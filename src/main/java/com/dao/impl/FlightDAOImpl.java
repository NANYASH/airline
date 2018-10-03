package com.dao.impl;


import com.dao.FlightDAO;
import com.util.CriteriaQueryBuilder;
import com.util.Filter;
import com.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FlightDAOImpl extends GenericDAO implements FlightDAO {

    CriteriaQueryBuilder criteriaQueryBuilder;

    @Autowired
    public FlightDAOImpl(CriteriaQueryBuilder criteriaQueryBuilder) {
        this.criteriaQueryBuilder = criteriaQueryBuilder;
    }

    private static final String SELECT_MOST_POPULAR_FLIGHT_DEP_CITY = "SELECT CITY_FROM FROM " +
            "(SELECT CITY_FROM,COUNT(CITY_FROM) AS counted " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE rownum <= 10 " +
            "GROUP BY CITY_FROM) " +
            "ORDER BY counted DESC";
    private static final String SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY = "SELECT CITY_TO FROM " +
            "(SELECT CITY_TO,COUNT(CITY_TO) AS counted " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE rownum <= 10 " +
            "GROUP BY CITY_TO) " +
            "ORDER BY counted DESC";


    @Override
    public List<Flight> flightsByDate(Filter filter) {
        return getEntityManager().createQuery(criteriaQueryBuilder.getFilterQuery(filter)).getResultList();
    }

    @Override
    public List<String> mostPopularTo() {
        return getEntityManager().createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY).getResultList();

    }

    @Override
    public List<String> mostPopularFrom() {
        return getEntityManager().createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_DEP_CITY).getResultList();
    }


}
