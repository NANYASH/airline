package com.dao.impl;


import com.dao.PassengerDAO;
import com.entity.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PassengerDAOImpl implements PassengerDAO{

    private static final String SELECT_REGULAR_PASSENGERS = "SELECT ID,LAST_NAME,NATIONALITY,DATE_OF_BIRTH,PASSPORT_CODE FROM " +
            "(SELECT PASSENGER.ID,LAST_NAME,NATIONALITY,DATE_OF_BIRTH,PASSPORT_CODE,COUNT(LAST_NAME) AS counted " +
            "FROM PASSENGER JOIN FLIGHT_PASSENGER ON PASSENGER.ID = FLIGHT_PASSENGER.ID_PASSENGER JOIN FLIGHT ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE to_char(DATE_FLIGHT, 'yyyy') = ? " +
            "GROUP BY PASSENGER.ID,LAST_NAME, NATIONALITY, DATE_OF_BIRTH, PASSPORT_CODE) " +
            "WHERE counted > 4";

    @PersistenceContext
    private EntityManager entityManager;

    public Passenger save(Passenger passenger){
        entityManager.persist(passenger);
        return passenger;
    }

    public Passenger update(Passenger passenger){
        entityManager.merge(passenger);
        return passenger;
    }

    public Passenger delete(long id){
        Passenger passenger = entityManager.find(Passenger.class,id);
        entityManager.remove(passenger);
        return passenger;
    }

    @Override
    public List<Passenger> regularPassengers(int year) {
        Query query = entityManager.createNativeQuery(SELECT_REGULAR_PASSENGERS,Passenger.class);
        query.setParameter(1,year);
        List<Passenger> passengers = query.getResultList();
        return passengers;
    }
}
