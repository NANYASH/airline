package com.service;


import com.entity.Plane;

import java.util.List;

public interface PlaneService {
    //planes older than 25 years
    List<Plane> oldPlanes();

    //more than 300 flights per year
    List<Plane> regularPlanes(int year);
}
