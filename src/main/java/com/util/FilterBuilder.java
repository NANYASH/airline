package com.util;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FilterBuilder {

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dateFlight;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dateTo;
    private String cityFrom;
    private String cityTo;
    private String model;


    public FilterBuilder createFilterByDate(Date dateFlight){
        this.dateFlight = dateFlight;
        return this;
    }
    public FilterBuilder createFilterByDates(Date dateFrom, Date dateTo){
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        return this;
    }
    public FilterBuilder createCityFromFilter(String cityFrom){
        this.cityFrom = cityFrom;
        return this;
    }
    public FilterBuilder createCityToFilter(String cityTo){
        this.cityTo = cityTo;
        return this;
    }

    public FilterBuilder createModelFilter(String model){
        this.model = model;
        return this;
    }

    public Filter built(){
        Filter filter = new Filter();

        if (cityFrom!=null)
            filter.setCityFrom(cityFrom);

        if (cityTo!=null)
            filter.setCityTo(cityTo);

        if (model != null)
            filter.setModel(model);

        if (dateFlight != null) {
            filter.setDateFlight(dateFlight);
            return filter;
        }
        if (dateFrom != null && dateTo !=null){
            filter.setDateTo(dateTo);
            filter.setDateFrom(dateFrom);
        }
        return filter;
    }

}
