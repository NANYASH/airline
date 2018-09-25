package com.controller;

import com.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PlaneController {

    private PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/oldPlanes", produces = "text/plain")
    @ResponseBody
    public String oldPlanes() {
        return planeService.oldPlanes().toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/regularPlanes", produces = "text/plain")
    @ResponseBody
    public String regularPlanes(@RequestParam int year) {
        return planeService.regularPlanes(year).toString();
    }
}
