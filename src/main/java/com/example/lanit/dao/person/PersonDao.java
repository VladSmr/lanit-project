package com.example.lanit.dao.person;

import com.example.lanit.model.Person;

public interface PersonDao {

    void addPerson(Person person);

    Person getPerson(Long id);

    Long getCount();

    void clearAll();
}
