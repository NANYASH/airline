package com.dao.impl;


import com.dao.FlightDAO;
import com.entity.Filter;
import com.entity.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FlightDAOImpl implements FlightDAO{

    private static final String SELECT_FLIGHT_BY_PARAMETERS = "";
    private static final String SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY = "";
    private static final String SELECT_MOST_POPULAR_FLIGHT_DEP_CITY = "";

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

    //top 10 flights by cityTo
    @Override
    public List<Flight> mostPopularTo() {
        return null;
    }

    //top 10 flights by cityFrom
    @Override
    public List<Flight> mostPopularFrom() {
        return null;
    }
}
