package com.service;


import com.entity.Passenger;

import java.util.List;

public interface PassengerService {
    
    List<Passenger> regularPassengers(int year);
}
