package com.example.lanit.service.car;

import com.example.lanit.view.CarView;

public interface CarService {

    void addCar(CarView car);

    Boolean checkIfExistsCar(Long id);

    Boolean checkVendorAndModel(String vendorAndModel);

    Boolean checkHorsePower(Integer horsePower);

    Boolean checkAll(CarView car);

    Long getCount();

    Long getUniqueVendor();

    void clearAll();
}
