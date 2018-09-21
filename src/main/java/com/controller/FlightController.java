package com.controller;


import com.entity.Filter;
import com.entity.Flight;
import com.exception.BadRequestException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
    public String flightsByDate(HttpServletRequest req) throws BadRequestException {
        return flightService.flightsByDate(mapToFilter(req)).toString();
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

    private Filter mapToFilter(HttpServletRequest req) throws BadRequestException {
        try {
            return mapper.readValue(
                    mapper.writeValueAsString(mapper.readTree(req.getInputStream()).path("filter")),
                    new TypeReference<Filter>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequestException("Cannot be mapped to Filter object");
        }
    }
}
