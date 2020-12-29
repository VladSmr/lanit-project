package com.example.lanit.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CarView {

    @NotNull
    public Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+[\\-][a-zA-Z0-9]+$")
    @NotEmpty
    public String model;

    @NotNull
    public Integer horsePower;

    @NotNull
    public Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}