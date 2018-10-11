package com.dao.impl;


import com.dao.FlightDAO;
import com.entity.Plane;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.Filter;
import com.entity.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class FlightDAOImpl extends GenericDAO implements FlightDAO {

    private static final String SELECT_MOST_POPULAR_FLIGHT_DEP_CITY = "SELECT CITY_FROM FROM " +
            "(SELECT CITY_FROM,COUNT(CITY_FROM) AS counted " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE rownum <= 10 " +
            "GROUP BY CITY_FROM) " +
            "ORDER BY counted DESC";
    private static final String SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY = "SELECT CITY_TO FROM " +
            "(SELECT CITY_TO,COUNT(CITY_TO) AS counted " +
            "FROM FLIGHT JOIN FLIGHT_PASSENGER ON FLIGHT.ID = FLIGHT_PASSENGER.ID_FLIGHT " +
            "WHERE rownum <= 10 " +
            "GROUP BY CITY_TO) " +
            "ORDER BY counted DESC";


    @Override
    public List<Flight> flightsByDate(Filter filter) {
        return findFlight(filter);
    }

    @Override
    public List<String> mostPopularTo() {
        return getEntityManager().createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_BY_ARR_CITY).getResultList();

    }

    @Override
    public List<String> mostPopularFrom() {
        return getEntityManager().createNativeQuery(SELECT_MOST_POPULAR_FLIGHT_DEP_CITY).getResultList();
    }

    private List<Flight> findFlight(Filter filter){
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> filterParms = mapper.convertValue(filter,Map.class);

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);
        Root<Flight> root = criteria.from(Flight.class);
        CriteriaQuery<Flight> select = criteria.select(root);
        Predicate predicate = builder.conjunction();
        Join<Flight,Plane> join;

        if (filter.getModel()!=null) {
            join = root.join("plane");
            predicate = builder.and(builder.equal(join.get("model"),filter.getModel()));
        }

        if (filter.getDateFlight() == null && filter.getDateFrom()!=null && filter.getDateTo()!=null)
            predicate = builder.and(builder.between(root.get("dateFlight"), filter.getDateFrom(),filter.getDateTo()));

        for(String param : filterParms.keySet()){
            if (filterParms.get(param)!=null && !param.equals("model") &&
                    !param.equals("dateFrom") && !param.equals("dateTo"))
                predicate = builder.and(predicate, builder.equal(root.get(param),filterParms.get(param)));
        }
        return getEntityManager().createQuery(select.where(predicate)).getResultList();
    }
}
