package com.service;


import com.util.Filter;
import com.entity.Flight;

import java.util.List;

public interface FlightService {
    
    List<Flight> flightsByDate(Filter filter);
    
    List<String> mostPopularTo();
    
    List<String> mostPopularFrom();
}
