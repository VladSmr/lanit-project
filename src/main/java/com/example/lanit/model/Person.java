package com.example.lanit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Person {

    @Id
    @NotNull
    private Long id;

    @Column(length = 50)
    @NotNull
    private String name;

    @NotNull
    private Date birthdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Car> cars;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}