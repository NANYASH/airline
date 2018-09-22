package com.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.FlightService;
import com.util.Filter;
import com.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class FlightController {

    private FlightService flightService;
    private ObjectMapper mapper;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
        this.mapper = new ObjectMapper();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flightsByDate", produces = "text/plain")
    @ResponseBody
    public String flightsByDate(@RequestParam(required = false) Date dateFlight,
                                @RequestParam(required = false) Date dateFrom,
                                @RequestParam(required = false) Date dateTo,
                                @RequestParam(required = false) String cityFrom,
                                @RequestParam(required = false) String cityTo,
                                @RequestParam(required = false) String model){
        Filter filter= new FilterBuilder()
                .createCityFromFilter(cityFrom)
                .createCityToFilter(cityTo)
                .createModelFilter(model)
                .createFilterByDate(dateFlight)
                .createFilterByDates(dateFrom,dateTo)
                .built();
        return flightService.flightsByDate(filter).toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mostPopularTo", produces = "text/plain")
    @ResponseBody
    public String mostPopularTo() {
        return flightService.mostPopularTo();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mostPopularFrom", produces = "text/plain")
    @ResponseBody
    public String mostPopularFrom(){
        return flightService.mostPopularFrom();
    }

}
