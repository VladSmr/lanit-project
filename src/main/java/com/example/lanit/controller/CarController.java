package com.example.lanit.controller;

import com.example.lanit.model.Person;
import com.example.lanit.model.mapper.MapperFacade;
import com.example.lanit.service.car.CarService;
import com.example.lanit.service.person.PersonService;
import com.example.lanit.view.CarView;
import com.example.lanit.view.PersonWithCarsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class CarController {

    private final CarService carService;
    private final PersonService personService;
    private final MapperFacade mapperFacade;

    @Autowired
    public CarController(CarService carService, PersonService personService, MapperFacade mapperFacade) {
        this.carService = carService;
        this.personService = personService;
        this.mapperFacade = mapperFacade;
    }

    @PostMapping("/car")
    public void addCar(@Valid
                       @RequestBody CarView car, HttpServletResponse response) {
        if (carService.checkAll(car) && personService.checkAge(car.getOwnerId())) {
            carService.addCar(car);
        } else {
            try {
                response.sendError(400, "Incorrect input data");
            } catch (IOException e) {
                throw new RuntimeException("There was a problem sending response");
            }
        }
    }

    @GetMapping("/personwithcars")
    public PersonWithCarsView getCars(@RequestParam
                                      @NotNull
                                      @Valid Long personId, HttpServletResponse response) {
        Person person = personService.getPerson(personId);
        if (person == null) {
            try {
                response.sendError(404, "Person with provided ID not found");
            } catch (IOException e) {
                throw new RuntimeException("There was a problem sending response");
            }
        }
        return mapperFacade.map(person, PersonWithCarsView.class);
    }
}