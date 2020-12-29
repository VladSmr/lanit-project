package com.example.lanit.dao.person;

import com.example.lanit.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
public class PersonDaoImpl implements PersonDao {

    private final EntityManager em;

    @Autowired
    public PersonDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addPerson(Person person) {
        em.persist(person);
    }

    @Override
    public Person getPerson(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Long getCount() {
        String query = "SELECT count(*) from Person";
        BigInteger number = (BigInteger) em.createNativeQuery(query).getSingleResult();
        return number.longValue();
    }

    @Override
    public void clearAll() {
        em.createNativeQuery("delete from person").executeUpdate();
    }
}