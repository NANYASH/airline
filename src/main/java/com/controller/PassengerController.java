package com.controller;


import com.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PassengerController {

    private PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/regularPassengers", produces = "text/plain")
    @ResponseBody
    public String regularPassengers() {
        return passengerService.regularPassengers().toString();
    }

}
