package com.dao.impl;


import com.dao.FlightDAO;
import com.util.Filter;
import com.entity.Flight;
import com.util.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FlightDAOImpl extends GenericDAO implements FlightDAO{

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


    public Flight save(Flight flight){
        super.getEntityManager().persist(flight);
        return flight;
    }

    public Flight update(Flight flight){
        super.getEntityManager().merge(flight);
        return flight;
    }

    public Flight delete(long id){
        Flight flight = super.getEntityManager().find(Flight.class,id);
        super.getEntityManager().remove(flight);
        return flight;
    }
    //by flightDate
    //fromDate/toDate
    //cityFrom
    //cityTo
    //planeModel
    @Override
    public List<Flight> flightsByDate(Filter filter) {
        String query = new QueryBuilder().buildQuery(filter);
        List<Flight> flights = super.getEntityManager().createNativeQuery(query,Flight.class).getResultList();
        return flights;
    }

    @Override
    public String mostPopularTo() {
      String city = super.getEntityManager().createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY).getSingleResult().toString();
      return city;
    }

    @Override
    public String mostPopularFrom() {
        String city = super.getEntityManager().createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_DEP_CITY).getSingleResult().toString();
        return city;
    }


}
