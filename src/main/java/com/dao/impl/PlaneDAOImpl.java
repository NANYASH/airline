package com.dao.impl;


import com.dao.PlaneDAO;
import com.entity.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PlaneDAOImpl implements PlaneDAO{

    private static final String SELECT_OLD_PLANES = "";
    private static final String SELECT_REGULAR_PLANES = "";

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

    //planes older than 25 years
    @Override
    public List<Plane> oldPlanes() {
        return null;
    }

    //more than 300 flights per year
    @Override
    public List<Plane> regularPlanes(int year) {
        return null;
    }
}
