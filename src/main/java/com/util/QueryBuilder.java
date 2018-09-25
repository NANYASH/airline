package com.util;

public class QueryBuilder {

    private Filter filter;
    private StringBuilder query;

    public QueryBuilder() {
        this.query = new StringBuilder("SELECT * FROM FLIGHT WHERE ID IS NOT NULL");
    }

    public QueryBuilder createFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    private QueryBuilder createFilterCityTo(){
        if (filter.getCityTo()!=null)
            this.query.append(" AND CITY_TO = \'"+filter.getCityTo()+"\'");
        return this;
    }

    private QueryBuilder createFilterCityFrom(){
        if (filter.getCityFrom()!=null)
            this.query.append(" AND CITY_FROM = \'"+filter.getCityFrom()+"\'");
        return this;
    }

    private QueryBuilder createDateFlight(){
        if (filter.getDateFlight()!=null)
            this.query.append(" AND DATE_FLIGHT = \'"+filter.getDateFlight()+"\'");
        return this;
    }

    private QueryBuilder createDatesFlight(){
        if (filter.getDateFrom()!=null)
            this.query.append(" AND DATE_FROM = \'"+filter.getDateFrom()+"\' AND DATE_TO = \'"+filter.getDateTo()+"\'");
        return this;
    }

    private QueryBuilder createModel(){
        if (filter.getModel()!=null)
            this.query.append(" AND DATE_FLIGHT = \'"+filter.getDateFlight()+"\'");
        return this;
    }

    public String buildQuery(){
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.createFilter(filter);
        queryBuilder.createDateFlight();
        queryBuilder.createDatesFlight();
        queryBuilder.createFilterCityFrom();
        queryBuilder.createFilterCityTo();
        queryBuilder.createModel();
        return queryBuilder.query.toString();
    }


}
