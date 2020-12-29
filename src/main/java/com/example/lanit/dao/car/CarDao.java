package com.example.lanit.dao.car;

import com.example.lanit.model.Car;

public interface CarDao {

    void addCar(Car car);

    Boolean checkIfExistsCar(Long id);

    Long getCount();

    Long getUniqueVendor();

    void clearAll();
}