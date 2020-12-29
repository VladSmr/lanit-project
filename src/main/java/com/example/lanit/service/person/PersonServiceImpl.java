package com.example.lanit.service.person;

import com.example.lanit.dao.person.PersonDao;
import com.example.lanit.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        if (personDao.getPerson(person.getId()) == null) {
            personDao.addPerson(person);
        } else {
            throw new RuntimeException("Person with provided ID already exists");
        }
    }

    @Override
    public Person getPerson(Long id) {
        return personDao.getPerson(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Boolean checkAge(Long id) {
        Person person = personDao.getPerson(id);
        if (person != null) {
            long today = System.currentTimeMillis();
            long birthdate = person.getBirthdate().getTime();
            long ageInMillis = today - birthdate;
            long ageInHours = ageInMillis / (1000 * 60 * 60);
            long ageInYears = ageInHours / (24 * 365);
            return ageInYears >= 18;
        } else {
            return false;
        }
    }

    @Override
    public Boolean checkDate(Date date) {
        Long today = System.currentTimeMillis();
        Long birthdate = date.getTime();
        return today - birthdate > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return personDao.getCount();
    }

    @Override
    @Transactional
    public void clearAll() {
        personDao.clearAll();
    }
}