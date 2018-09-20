package com.service.impl;


import com.dao.PassengerDAO;
import com.entity.Passenger;
import com.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService{
    private PassengerDAO passengerDAO;

    @Autowired
    public PassengerServiceImpl(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    @Override
    public List<Passenger> regularPassengers() {
        return null;
    }
}
