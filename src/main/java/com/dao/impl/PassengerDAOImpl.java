package com.dao.impl;


import com.dao.PassengerDAO;
import com.entity.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PassengerDAOImpl extends GenericDAO implements PassengerDAO{

    private static final String SELECT_REGULAR_PASSENGERS = "SELECT PASSENGER.ID,LAST_NAME,NATIONALITY,DATE_OF_BIRTH,PASSPORT_CODE " +
            "FROM PASSENGER JOIN FLIGHT_PASSENGER ON PASSENGER.ID = FLIGHT_PASSENGER.ID_PASSENGER JOIN FLIGHT ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE to_char(DATE_FLIGHT, 'yyyy') = ? " +
            "GROUP BY PASSENGER.ID,LAST_NAME, NATIONALITY, DATE_OF_BIRTH, PASSPORT_CODE " +
            "HAVING COUNT(PASSENGER.ID) > 25";


    public Passenger save(Passenger passenger){
        super.getEntityManager().persist(passenger);
        return passenger;
    }

    public Passenger update(Passenger passenger){
        super.getEntityManager().merge(passenger);
        return passenger;
    }

    public Passenger delete(long id){
        Passenger passenger = super.getEntityManager().find(Passenger.class,id);
        super.getEntityManager().remove(passenger);
        return passenger;
    }

    @Override
    public List<Passenger> regularPassengers(int year) {
        Query query = super.getEntityManager().createNativeQuery(SELECT_REGULAR_PASSENGERS,Passenger.class);
        query.setParameter(1,year);
        List<Passenger> passengers = query.getResultList();
        return passengers;
    }
}
