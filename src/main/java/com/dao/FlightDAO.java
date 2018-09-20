package com.dao;


import com.entity.Filter;
import com.entity.Flight;

import java.util.List;

public interface FlightDAO {
    
    List<Flight> flightsByDate(Filter filter);
    
    List<Flight> mostPopularTo();
    
    List<Flight> mostPopularFrom();
}
