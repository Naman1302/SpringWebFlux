package com.Airtel.webflux.Entity.Supportclass;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Address {
    @Min(value=1)
    private String houseNumber;
    @NotBlank(message = "City should be mentioned")
    private String city;
    @NotBlank(message = "State should be mentioned")
    private String state;

    public Address(String houseNumber, String city, String state) {
        this.houseNumber = houseNumber;
        this.city = city;
        this.state = state;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
