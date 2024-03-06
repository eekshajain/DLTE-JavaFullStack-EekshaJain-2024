package org.middleware;

import java.io.Serializable;

public class EmployeeAddress implements Serializable {
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

    public String getTemporaryHouseName() {
        return temporaryHouseName;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "temporaryHouseName='" + temporaryHouseName + '\'' +
                ", temporaryHouseStreet='" + temporaryHouseStreet + '\'' +
                ", temporaryCityName='" + temporaryCityName + '\'' +
                ", temporaryStateName='" + temporaryStateName + '\'' +
                ", temporaryPinCode=" + temporaryPinCode +
                ", permanentHouseName='" + permanentHouseName + '\'' +
                ", permanentHouseStreet='" + permanentHouseStreet + '\'' +
                ", permanentCityName='" + permanentCityName + '\'' +
                ", permanentStateName='" + permanentStateName + '\'' +
                ", permanentPinCode=" + permanentPinCode +
                '}';
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
}
