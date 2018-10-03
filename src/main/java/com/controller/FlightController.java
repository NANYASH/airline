package com.controller;


import com.exception.BadRequestException;
import com.service.FlightService;
import com.util.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flightsByDate", produces = "text/plain")
    @ResponseBody
    public String flightsByDate(@RequestParam(required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date dateFlight,
                                @RequestParam(required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date dateFrom,
                                @RequestParam(required = false) @DateTimeFormat(pattern ="dd/MM/yyyy")Date dateTo,
                                @RequestParam(required = false) String cityFrom,
                                @RequestParam(required = false) String cityTo,
                                @RequestParam(required = false) String model) throws BadRequestException {
        Filter filter= new Filter();
        filter.setDateFlight(dateFlight);
        filter.setDateFrom(dateFrom);
        filter.setDateTo(dateTo);
        filter.setCityFrom(cityFrom);
        filter.setCityTo(cityTo);
        filter.setModel(model);
        return flightService.flightsByDate(filter).toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mostPopularTo", produces = "text/plain")
    @ResponseBody
    public String mostPopularTo() {
        return flightService.mostPopularTo().toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mostPopularFrom", produces = "text/plain")
    @ResponseBody
    public String mostPopularFrom(){
        return flightService.mostPopularFrom().toString();
    }
}
