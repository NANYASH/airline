package com.dao.impl;


import com.dao.FlightDAO;
import com.util.Filter;
import com.entity.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FlightDAOImpl implements FlightDAO{

    private static String SELECT_FLIGHT_BY_PARAMETERS = "SELECT * FROM FLIGHT WHERE ";

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
        List<Flight> flights = entityManager.createNativeQuery(buidQuery(filter),Flight.class).getResultList();
        return flights;
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

    private String buidQuery(Filter filter){
        String queryString = SELECT_FLIGHT_BY_PARAMETERS;

        if (filter.getCityTo()!= null) {
            if (!queryString.equals(SELECT_FLIGHT_BY_PARAMETERS)){
                queryString +=" AND CITY_TO = \'"+filter.getCityTo()+"\'";
            }else {
                queryString += " CITY_TO = \'"+filter.getCityTo()+"\'";
            }
        }
        if (filter.getCityFrom()!= null) {
            if (!queryString.equals(SELECT_FLIGHT_BY_PARAMETERS)){
                queryString +=" AND CITY_FROM = \'"+filter.getCityFrom()+"\'";
            }else {
                queryString += " CITY_FROM = \'"+filter.getCityFrom()+"\'";
            }
        }
        if (filter.getDateFlight()!= null) {
            if (!queryString.equals(SELECT_FLIGHT_BY_PARAMETERS)){
                queryString +=" AND DATE_FLIGHT = \'"+filter.getDateFlight()+"\'";
            }else {
                queryString += " DATE_FLIGHT = \'"+filter.getDateFlight()+"\'";
            }
        }
        if (filter.getDateFlight()!= null) {
            if (!queryString.equals(SELECT_FLIGHT_BY_PARAMETERS)){
                queryString +=" AND DATE_FROM = \'"+filter.getDateFrom()+"\'";
                queryString +=" AND DATE_TO = \'"+filter.getDateTo()+"\'";
            }else {
                queryString += " DATE_FROM = \'"+filter.getDateFrom()+"\'";
                queryString +=" AND DATE_TO = \'"+filter.getDateTo()+"\'";
            }
        }
        if (filter.getModel()!= null) {
            if (!queryString.equals(SELECT_FLIGHT_BY_PARAMETERS)){
                queryString +=" AND MODEL = \'"+filter.getModel()+"\'";
            }else {
                queryString +=" MODEL = \'"+filter.getModel()+"\'";
            }
        }
        return queryString;
    }

}
