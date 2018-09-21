package com.dao.impl;


import com.dao.FlightDAO;
import com.entity.Filter;
import com.entity.Flight;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FlightDAOImpl implements FlightDAO{

    private static final String SELECT_FLIGHT_BY_PARAMETERS = "";
    private static final String SELECT_MOST_POPULAR_FLIGHT_DEP_CITY = "SELECT CITY_FROM FROM " +
            "(SELECT CITY_FROM,COUNT(CITY_FROM) AS counted " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE rownum = 1 " +
            "GROUP BY CITY_FROM) " +
            "ORDER BY counted DESC";
    private static final String SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY = "SELECT CITY_TO FROM " +
            "(SELECT CITY_TO,COUNT(CITY_TO) AS counted " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE rownum = 1 " +
            "GROUP BY CITY_TO) " +
            "ORDER BY counted DESC";

    @PersistenceContext
    private EntityManager entityManager;


    public Flight save(Flight flight){
        entityManager.persist(flight);
        return flight;
    }

    public Flight update(Flight flight){
        entityManager.merge(flight);
        return flight;
    }

    public Flight delete(long id){
        Flight flight = entityManager.find(Flight.class,id);
        entityManager.remove(flight);
        return flight;
    }
    //by flightDate
    //fromDate/toDate
    //cityFrom
    //cityTo
    //planeModel
    @Override
    public List<Flight> flightsByDate(Filter filter) {
        return null;
    }

    @Override
    public String mostPopularTo() {
      String city = entityManager.createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY).getSingleResult().toString();
      return city;
    }

    @Override
    public String mostPopularFrom() {
        String city = entityManager.createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_DEP_CITY).getSingleResult().toString();
        return city;
    }
}
