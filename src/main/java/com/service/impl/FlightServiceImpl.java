package com.service.impl;


import com.dao.FlightDAO;
import com.entity.Filter;
import com.entity.Flight;
import com.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{
    private FlightDAO flightDAO;

    @Autowired
    public FlightServiceImpl(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    @Override
    public List<Flight> flightsByDate(Filter filter) {
        return null;
    }

    @Override
    public List<Flight> mostPopularTo() {
        return flightDAO.mostPopularTo();
    }

    @Override
    public List<Flight> mostPopularFrom(){
        return flightDAO.mostPopularFrom();
    }
}
