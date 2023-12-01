package com.Airtel.webflux.Entity.Supportclass;

public class Address {
    private String houseNumber;
    private String city;
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
