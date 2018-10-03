package com.util;

import com.dao.impl.GenericDAO;
import com.entity.Flight;
import com.entity.Plane;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CriteriaQueryBuilder extends GenericDAO{
    private Filter filter;
    CriteriaBuilder builder;
    CriteriaQuery<Flight> criteria;
    Root<Flight> root;
    CriteriaQuery<Flight> select;
    Join<Flight,Plane> join;
    List<Predicate> predicate;

    public void init(Filter filter) {
        this.filter = filter;
        this.builder = getEntityManager().getCriteriaBuilder();
        this.criteria = builder.createQuery(Flight.class);
        this.root = criteria.from(Flight.class);
        this.select = this.criteria.select(root);
        this.predicate = new ArrayList<>();
    }

    private CriteriaQueryBuilder createFilterCityTo(){
        if (filter.getCityTo()!=null)
            predicate.add(this.builder.equal(root.get("cityTo"),filter.getCityTo()));
        return this;
    }

    private CriteriaQueryBuilder createFilterCityFrom(){
        if (filter.getCityFrom()!=null)
            predicate.add(this.builder.equal(root.get("cityFrom"),filter.getCityFrom()));
        return this;
    }

    private CriteriaQueryBuilder createDateFlight(){
        if (filter.getDateFlight()!=null)
            predicate.add(this.builder.equal(root.get("dateFlight"),filter.getDateFlight()));
        return this;
    }

    private CriteriaQueryBuilder createDatesFlight(){
        if (filter.getDateFrom()!=null && filter.getDateTo()!=null) {
            predicate.add(this.builder.between(root.get("dateFlight"), filter.getDateFrom(),filter.getDateTo()));
        }
        return this;
    }
    private CriteriaQueryBuilder createModel(){
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

    public CriteriaQuery getFilterQuery(Filter filter){
        init(filter);
        buildPredicates();
        return select.where(builder.or(predicate.toArray(new Predicate[0])));
    }
}
