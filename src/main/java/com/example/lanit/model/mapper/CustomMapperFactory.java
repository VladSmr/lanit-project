package com.example.lanit.model.mapper;

import com.example.lanit.model.Car;
import com.example.lanit.model.Person;
import com.example.lanit.view.CarView;
import com.example.lanit.view.CarsWithPersonView;
import com.example.lanit.view.PersonWithCarsView;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class CustomMapperFactory implements FactoryBean<MapperFactory> {

    @Override
    public MapperFactory getObject() {
        MapperFactory factory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        factory.classMap(Car.class, CarView.class)
                .field("person.id", "ownerId")
                .byDefault()
                .register();
        factory.classMap(Car.class, CarsWithPersonView.class)
                .byDefault()
                .register();
        factory.classMap(Person.class, PersonWithCarsView.class)
                .byDefault()
                .register();
        return factory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
