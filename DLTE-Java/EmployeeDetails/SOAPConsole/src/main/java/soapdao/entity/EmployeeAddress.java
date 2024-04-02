package soapdao.entity;


public class EmployeeAddress {
    private String houseName;
    private String houseStreet;
    private String cityName;
    private String stateName;
    private int pinCode;

    public EmployeeAddress(String houseName, String houseStreet, String cityName, String stateName, int pinCode) {
        this.houseName = houseName;
        this.houseStreet = houseStreet;
        this.cityName = cityName;
        this.stateName = stateName;
        this.pinCode = pinCode;
    }

    public EmployeeAddress() {
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

