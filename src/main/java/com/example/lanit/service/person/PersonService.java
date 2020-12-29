package com.example.lanit.service.person;

import com.example.lanit.model.Person;

import java.util.Date;

public interface PersonService {

    void addPerson(Person person);

    Person getPerson(Long id);

    Boolean checkAge(Long id);

    Boolean checkDate(Date date);

    Long getCount();

    void clearAll();
}
