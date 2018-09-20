package com.dao.impl;


import com.dao.PassengerDAO;
import com.entity.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PassengerDAOImpl implements PassengerDAO{

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
}
