package com;


import com.dao.PlaneDAO;
import com.dao.impl.PlaneDAOImpl;
import com.entity.Plane;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        PlaneDAO planeDAO= new PlaneDAOImpl();
        Plane plane = new Plane();
        plane.setModel("Model1");
        plane.setCode("01");
        plane.setYearProduced(new Date("10.11.1990"));
        plane.setAvgFuelConsumption(1500d);
    }
}
