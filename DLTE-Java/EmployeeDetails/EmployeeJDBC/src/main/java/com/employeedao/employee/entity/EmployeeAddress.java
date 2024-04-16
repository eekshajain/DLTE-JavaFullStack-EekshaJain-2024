package com.employeedao.employee.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeAddress {
    @NotNull
    private String houseName;

    @NotNull
    private String houseStreet;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$", message = "City name must contain only letters and spaces")
    private String cityName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z ]+$", message = "State name must contain only letters and spaces")
    private String stateName;
    @Range(min = 100000, max = 999999, message = "PIN code must be a 6-digit number")
    private int pinCode;

    public EmployeeAddress() {
    }

    public EmployeeAddress(String houseName, String houseStreet, String cityName, String stateName, int pinCode) {
        this.houseName = houseName;
        this.houseStreet = houseStreet;
        this.cityName = cityName;
        this.stateName = stateName;
        this.pinCode = pinCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseStreet() {
        return houseStreet;
    }

    public void setHouseStreet(String houseStreet) {
        this.houseStreet = houseStreet;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
