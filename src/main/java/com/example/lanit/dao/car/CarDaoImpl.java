package com.example.lanit.dao.car;

import com.example.lanit.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CarDaoImpl implements CarDao {

    private final EntityManager em;

    @Autowired
    public CarDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addCar(Car car) {
        em.persist(car);
    }

    @Override
    public Boolean checkIfExistsCar(Long id) {
        try {
            Car car = em.find(Car.class, id);
            return car != null;
        } catch (NullPointerException | EntityNotFoundException e) {
            return false;
        }
    }

    @Override
    public Long getCount() {
        String query = "SELECT count(*) from Car";
        BigInteger number = (BigInteger) em.createNativeQuery(query).getSingleResult();
        return number.longValue();
    }

    @Override
    public Long getUniqueVendor() {
        Query query = em.createNativeQuery("SELECT distinct vendor from Car");
        List<String> vendors = query.getResultList();
        Set<String> uniqueVendors = new HashSet<>();
        for (String s : vendors) {
            uniqueVendors.add(s.toUpperCase());
        }
        return (long) uniqueVendors.size();
    }

    @Override
    public void clearAll() {
        em.createNativeQuery("delete from car").executeUpdate();
    }
}