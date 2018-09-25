package com.dao;

import com.entity.Plane;

import java.util.List;


public interface PlaneDAO {
    
    List<Plane> oldPlanes();
    
    List<Plane> regularPlanes(int year);
}
