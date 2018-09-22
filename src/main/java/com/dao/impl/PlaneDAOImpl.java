package com.dao.impl;


import com.dao.PlaneDAO;
import com.entity.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PlaneDAOImpl implements PlaneDAO{

    private static final String SELECT_OLD_PLANES = "SELECT * FROM PLANE " +
            "where trunc(sysdate) -  trunc(PLANE.YEAR_PRODUCED) > 25*365";
    private static final String SELECT_REGULAR_PLANES = "SELECT ID,PLANE_MODEL,CODE,YEAR_PRODUCED,AVG_FUEL_CONSUMPTION FROM " +
            "(SELECT PLANE.ID,PLANE_MODEL,CODE,YEAR_PRODUCED,AVG_FUEL_CONSUMPTION,COUNT(ID_PLANE) AS counted " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT JOIN PLANE ON FLIGHT.ID_PLANE = PLANE.ID " +
            "WHERE to_char(DATE_FLIGHT, 'yyyy') = ? " +
            "GROUP BY PLANE.ID, PLANE_MODEL, CODE, YEAR_PRODUCED, AVG_FUEL_CONSUMPTION) " +
            "WHERE counted > 300";

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
        List<Plane> planes = entityManager.createNativeQuery(SELECT_OLD_PLANES,Plane.class).getResultList();
        return planes;
    }

    //more than 300 flights per year
    @Override
    public List<Plane> regularPlanes(int year) {
        Query query = entityManager.createNativeQuery(SELECT_REGULAR_PLANES,Plane.class);
        query.setParameter(1,year);
        List<Plane> planes = query.getResultList();
        return planes;
    }
}
