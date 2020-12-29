package com.example.lanit.controller;

import com.example.lanit.service.car.CarService;
import com.example.lanit.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class ClearController {

    private final CarService carService;
    private final PersonService personService;

    @Autowired
    public ClearController(CarService carService, PersonService personService) {
        this.carService = carService;
        this.personService = personService;
    }

    @PostMapping("/clear")
    public void clearAll() {
        carService.clearAll();
        personService.clearAll();
    }
}