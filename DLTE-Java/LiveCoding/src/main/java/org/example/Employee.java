package org.example;

public class Employee {
      private String firstName;
      private String middleName;
      private String lastName;
      private int employeeID;
      private String officialEmailID;
      private String temporaryHouseName;
      private String temporaryHouseStreet;
      private String temporaryCityName;
      private String temporaryStateName;
      private int temporaryPinCode;
      private String permanentHouseName;
      private String permanentHouseStreet;
      private String permanentCityName;
      private String permanentStateName;
      private int permanentPinCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getOfficialEmailID() {
        return officialEmailID;
    }

    public void setOfficialEmailID(String officialEmailID) {
        this.officialEmailID = officialEmailID;
    }

    public String getTemporaryHouseName() {
        return temporaryHouseName;
    }

    public void setTemporaryHouseName(String temporaryHouseName) {
        this.temporaryHouseName = temporaryHouseName;
    }

    public String getTemporaryHouseStreet() {
        return temporaryHouseStreet;
    }

    public void setTemporaryHouseStreet(String temporaryHouseStreet) {
        this.temporaryHouseStreet = temporaryHouseStreet;
    }

    public String getTemporaryCityName() {
        return temporaryCityName;
    }

    public void setTemporaryCityName(String temporaryCityName) {
        this.temporaryCityName = temporaryCityName;
    }

    public String getTemporaryStateName() {
        return temporaryStateName;
    }

    public void setTemporaryStateName(String temporaryStateName) {
        this.temporaryStateName = temporaryStateName;
    }

    public int getTemporaryPinCode() {
        return temporaryPinCode;
    }

    public void setTemporaryPinCode(int temporaryPinCode) {
        this.temporaryPinCode = temporaryPinCode;
    }

    public String getPermanentHouseName() {
        return permanentHouseName;
    }

    public void setPermanentHouseName(String permanentHouseName) {
        this.permanentHouseName = permanentHouseName;
    }

    public String getPermanentHouseStreet() {
        return permanentHouseStreet;
    }

    public void setPermanentHouseStreet(String permanentHouseStreet) {
        this.permanentHouseStreet = permanentHouseStreet;
    }

    public String getPermanentCityName() {
        return permanentCityName;
    }

    public void setPermanentCityName(String permanentCityName) {
        this.permanentCityName = permanentCityName;
    }

    public String getPermanentStateName() {
        return permanentStateName;
    }

    public void setPermanentStateName(String permanentStateName) {
        this.permanentStateName = permanentStateName;
    }

    public int getPermanentPinCode() {
        return permanentPinCode;
    }

    public void setPermanentPinCode(int permanentPinCode) {
        this.permanentPinCode = permanentPinCode;
    }

    public Employee() {
    }

    public Employee(String firstName, String middleName, String lastName, int employeeID, String officialEmailID, String temporaryHouseName, String temporaryHouseStreet, String temporaryCityName, String temporaryStateName, int temporaryPinCode, String permanentHouseName, String permanentHouseStreet, String permanentCityName, String permanentStateName, int permanentPinCode) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.officialEmailID = officialEmailID;
        this.temporaryHouseName = temporaryHouseName;
        this.temporaryHouseStreet = temporaryHouseStreet;
        this.temporaryCityName = temporaryCityName;
        this.temporaryStateName = temporaryStateName;
        this.temporaryPinCode = temporaryPinCode;
        this.permanentHouseName = permanentHouseName;
        this.permanentHouseStreet = permanentHouseStreet;
        this.permanentCityName = permanentCityName;
        this.permanentStateName = permanentStateName;
        this.permanentPinCode = permanentPinCode;
    }
}
