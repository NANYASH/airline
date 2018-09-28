package com.dao;


import com.util.Filter;
import com.entity.Flight;

import java.util.List;

public interface FlightDAO {
    
    List<Flight> flightsByDate(Filter filter);
    
    List<String> mostPopularTo();
    
    List<String> mostPopularFrom();
}
