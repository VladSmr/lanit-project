package com.example.lanit.service.car;

import com.example.lanit.dao.car.CarDao;
import com.example.lanit.dao.person.PersonDao;
import com.example.lanit.model.Car;
import com.example.lanit.model.Person;
import com.example.lanit.model.mapper.MapperFacade;
import com.example.lanit.view.CarView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CarServiceImpl implements CarService {

    private final CarDao carDao;
    private final MapperFacade mapperFacade;
    private final PersonDao personDao;

    @Autowired
    public CarServiceImpl(CarDao carDao, MapperFacade mapperFacade, PersonDao personDao) {
        this.carDao = carDao;
        this.mapperFacade = mapperFacade;
        this.personDao = personDao;
    }

    @Override
    @Transactional
    public void addCar(CarView carView) {
        Car car = new Car();
        car.setId(carView.getId());
        String[] words = carView.getModel().split("[\\-]");
        car.setVendor(words[0]);
        car.setModel(words[1]);
        car.setHorsePower(carView.getHorsePower());
        Person person = personDao.getPerson(carView.getOwnerId());
        car.setPerson(person);
        carDao.addCar(car);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean checkIfExistsCar(Long id) {
        return carDao.checkIfExistsCar(id);
    }

    @Override
    public Boolean checkVendorAndModel(String vendorAndModel) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+[\\-][a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(vendorAndModel);
        return matcher.find();
    }

    @Override
    public Boolean checkHorsePower(Integer horsePower) {
        return horsePower > 0;
    }

    @Override
    public Boolean checkAll(CarView car) {
        Boolean notExistsCar = !checkIfExistsCar(car.getId());
        Boolean vendorAndModel = checkVendorAndModel(car.getModel());
        Boolean horsePower = checkHorsePower(car.getHorsePower());
        return (notExistsCar && vendorAndModel && horsePower);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return carDao.getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getUniqueVendor() {
        return carDao.getUniqueVendor();
    }

    @Override
    @Transactional
    public void clearAll() {
        carDao.clearAll();
    }
}