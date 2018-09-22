package com.service;


import com.entity.Plane;

import java.util.List;

public interface PlaneService {

    List<Plane> oldPlanes();
    
    List<Plane> regularPlanes(int year);
}
