package com.util;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Filter {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFlight;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String cityFrom;
    private String cityTo;
    private String model;

    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (dateFlight != null ? !dateFlight.equals(filter.dateFlight) : filter.dateFlight != null) return false;
        if (dateFrom != null ? !dateFrom.equals(filter.dateFrom) : filter.dateFrom != null) return false;
        if (dateTo != null ? !dateTo.equals(filter.dateTo) : filter.dateTo != null) return false;
        if (cityFrom != null ? !cityFrom.equals(filter.cityFrom) : filter.cityFrom != null) return false;
        if (cityTo != null ? !cityTo.equals(filter.cityTo) : filter.cityTo != null) return false;
        return model != null ? model.equals(filter.model) : filter.model == null;
    }

    @Override
    public int hashCode() {
        int result = dateFlight != null ? dateFlight.hashCode() : 0;
        result = 31 * result + (dateFrom != null ? dateFrom.hashCode() : 0);
        result = 31 * result + (dateTo != null ? dateTo.hashCode() : 0);
        result = 31 * result + (cityFrom != null ? cityFrom.hashCode() : 0);
        result = 31 * result + (cityTo != null ? cityTo.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }
}
