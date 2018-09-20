package com.controller;

import com.entity.Plane;
import com.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PlaneController {

    private PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/oldPlanes", produces = "text/plain")
    @ResponseBody
    public List<Plane> oldPlanes() {
        return planeService.oldPlanes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete", produces = "text/plain")
    @ResponseBody
    public List<Plane> regularPlanes(@RequestParam int year) {
        return planeService.regularPlanes(year);
    }
}
