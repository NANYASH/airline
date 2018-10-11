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
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    private List<Flight> findFlight(Filter filter) {
        Map<String, Object> filterParms = new ObjectMapper().convertValue(filter, Map.class);

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Flight> criteria = builder.createQuery(Flight.class);

        Root<Flight> root = criteria.from(Flight.class);
        Join<Flight, Plane> join;

        Predicate predicate  = builder.conjunction();

        List<String> params = filterParms.entrySet().stream()
                .filter(param -> param.getValue() != null)
                .filter(param -> !param.getKey().equals("dateTo"))
                .filter(param -> !param.getKey().equals("dateFrom"))
                .map(param -> param.getKey())
                .collect(Collectors.toList());

        for (String param : params) {
            if (param.equals("model")) {
                join = root.join("plane");
                predicate = builder.and(predicate, builder.equal(join.get(param), filter.getModel()));
                continue;
            }
            predicate = builder.and(predicate, builder.equal(root.get(param), filterParms.get(param)));
        }

        if (filter.getDateFrom() != null && filter.getDateTo() != null)
            predicate = builder.and(predicate, builder.between(root.get("dateFlight"), filter.getDateFrom(), filter.getDateTo()));

        return getEntityManager().createQuery(criteria.select(root).where(predicate)).getResultList();
    }
}
