package com.service;


import com.entity.Filter;
import com.entity.Flight;

import java.util.List;

public interface FlightService {
    //by flightDate
    //fromDate/toDate
    //cityFrom
    //cityTo
    //planeModel
    List<Flight> flightsByDate(Filter filter);

    //top 10 flights by cityTo
    String mostPopularTo();

    //top 10 flights by cityFrom
    String mostPopularFrom();
}
