package com.service.impl;



import com.dao.PlaneDAO;
import com.entity.Plane;
import com.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService{
    PlaneDAO planeDAO;

    @Autowired
    public PlaneServiceImpl(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    @Override
    public List<Plane> oldPlanes() {
        return null;
    }

    @Override
    public List<Plane> regularPlanes(int year) {
        return null;
    }
}
