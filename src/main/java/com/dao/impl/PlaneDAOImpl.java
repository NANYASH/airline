package com.dao.impl;


import com.dao.PlaneDAO;
import com.entity.Plane;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PlaneDAOImpl extends GenericDAO implements PlaneDAO{

    private static final String SELECT_OLD_PLANES = "SELECT * FROM PLANE " +
            "where trunc(sysdate) -  trunc(PLANE.YEAR_PRODUCED) > 25*365";
    private static final String SELECT_REGULAR_PLANES = "SELECT PLANE.ID,PLANE_MODEL,CODE,YEAR_PRODUCED,AVG_FUEL_CONSUMPTION " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT JOIN PLANE ON FLIGHT.ID_PLANE = PLANE.ID " +
            "WHERE to_char(DATE_FLIGHT, 'yyyy') = ? " +
            "GROUP BY PLANE.ID, PLANE_MODEL, CODE, YEAR_PRODUCED, AVG_FUEL_CONSUMPTION " +
            "HAVING COUNT(ID_PLANE)>300";



    public Plane save(Plane plane){
        super.getEntityManager().persist(plane);
        return plane;
    }

    public Plane update(Plane plane){
        super.getEntityManager().merge(plane);
        return plane;
    }

    public Plane delete(long id){
        Plane plane = super.getEntityManager().find(Plane.class,id);
        super.getEntityManager().remove(plane);
        return plane;
    }

    //planes older than 25 years
    @Override
    public List<Plane> oldPlanes() {
        List<Plane> planes = super.getEntityManager().createNativeQuery(SELECT_OLD_PLANES,Plane.class).getResultList();
        return planes;
    }

    //more than 300 flights per year
    @Override
    public List<Plane> regularPlanes(int year) {
        Query query = super.getEntityManager().createNativeQuery(SELECT_REGULAR_PLANES,Plane.class);
        query.setParameter(1,year);
        List<Plane> planes = query.getResultList();
        return planes;
    }
}
