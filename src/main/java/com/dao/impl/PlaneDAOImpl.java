package com.dao.impl;


import com.dao.PlaneDAO;
import com.entity.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PlaneDAOImpl implements PlaneDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public Plane save(Plane plane){
        entityManager.persist(plane);
        return plane;
    }

    public Plane update(Plane plane){
        entityManager.merge(plane);
        return plane;
    }

    public Plane delete(long id){
        Plane plane = entityManager.find(Plane.class,id);
        entityManager.remove(plane);
        return plane;
    }
}
