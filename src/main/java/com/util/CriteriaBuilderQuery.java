package com.util;

import com.dao.impl.GenericDAO;
import com.entity.Flight;
import com.entity.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Repository
public class CriteriaBuilderQuery extends GenericDAO{
    private Filter filter;
    CriteriaBuilder builder;
    CriteriaQuery<Flight> criteria;
    Root<Flight> root;
    CriteriaQuery<Flight> select;
    Join<Flight,Plane> join;
    List<Predicate> predicate;

    public void init() {
        this.builder = getEntityManager().getCriteriaBuilder();
        this.criteria = builder.createQuery(Flight.class);
        this.root = criteria.from(Flight.class);
        this.select = this.criteria.select(root);
        this.predicate = new ArrayList<>();
    }

    public CriteriaBuilderQuery createFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    private CriteriaBuilderQuery createFilterCityTo(){
        if (filter.getCityTo()!=null)
            predicate.add(this.builder.equal(root.get("cityTo"),filter.getCityTo()));
        return this;
    }

    private CriteriaBuilderQuery createFilterCityFrom(){
        if (filter.getCityFrom()!=null)
            predicate.add(this.builder.equal(root.get("cityFrom"),filter.getCityFrom()));
        return this;
    }

    private CriteriaBuilderQuery createDateFlight(){
        if (filter.getDateFlight()!=null)
            predicate.add(this.builder.equal(root.get("dateFlight"),filter.getDateFlight()));
        return this;
    }

    private CriteriaBuilderQuery createDatesFlight(){
        if (filter.getDateFrom()!=null) {
            predicate.add(this.builder.between(root.get("dateFlight"), filter.getDateFrom(),filter.getDateTo()));
        }
        return this;
    }
    private CriteriaBuilderQuery createModel(){
        if (filter.getModel()!=null) {
            join = root.join("plane");
            predicate.add(this.builder.equal(join.get("model"),filter.getModel()));
        }
        return this;
    }

    public void buildPredicates(){
        createModel();
        createFilterCityTo();
        createFilterCityFrom();
        createDateFlight();
        createDatesFlight();
    }

    public List<Flight> getListOfFlights(Filter filter){
        init();
        createFilter(filter);
        buildPredicates();
        select.where(builder.or(predicate.toArray(new Predicate[0])));
        return getEntityManager().createQuery(select).getResultList();
    }
}
