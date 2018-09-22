package com.service;


import com.util.Filter;
import com.entity.Flight;

import java.util.List;

public interface FlightService {
    
    List<Flight> flightsByDate(Filter filter);
    
    String mostPopularTo();
    
    String mostPopularFrom();
}
