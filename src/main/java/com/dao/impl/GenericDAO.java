package com.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
