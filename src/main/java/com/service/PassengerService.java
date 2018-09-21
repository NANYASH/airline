package com.service;


import com.entity.Passenger;

import java.util.List;

public interface PassengerService {
    //more than 25 flights per year
    List<Passenger> regularPassengers(int year);
}
