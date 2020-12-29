package com.example.lanit.controller;

import com.example.lanit.service.car.CarService;
import com.example.lanit.service.person.PersonService;
import com.example.lanit.view.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class StatisticController {

    private final CarService carService;
    private final PersonService personService;

    @Autowired
    public StatisticController(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @GetMapping("/statistics")
    public Statistic getStatistics() {
        Statistic statistic = new Statistic();
        statistic.setPersonCount(personService.getCount());
        statistic.setCarCount(carService.getCount());
        statistic.setUniqueVendorCount(carService.getUniqueVendor());
        return statistic;
    }
}