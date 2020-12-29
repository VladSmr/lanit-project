package com.example.lanit.controller;

import com.example.lanit.model.Person;
import com.example.lanit.service.person.PersonService;
import com.example.lanit.view.PersonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public void addPerson(@Valid
                          @RequestBody PersonView personView, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        Date date;
        try {
            date = sdf.parse(personView.getBirthdate());
            if (personService.checkDate(date)) {
                Person person = new Person();
                person.setId(personView.getId());
                person.setName(personView.getName());
                person.setBirthdate(date);
                personService.addPerson(person);
            } else {
                response.sendError(400, "Are you sure about your age?");
            }
        } catch (ParseException e) {
            try {
                response.sendError(400, "Incorrect date format. Try again");
            } catch (IOException ex) {
                throw new RuntimeException("There was a problem sending response");
            }
        } catch (IOException e) {
            throw new RuntimeException("There was a problem sending response");
        }
    }
}