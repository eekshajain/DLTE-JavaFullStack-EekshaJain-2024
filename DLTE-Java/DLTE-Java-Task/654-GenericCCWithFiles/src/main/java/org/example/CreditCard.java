package org.example;

import java.io.Serializable;
import java.util.Date;

public class CreditCard implements Serializable {
    private String CreditCardHolder;
    private Integer CreditCardCVV;
    private String CreditCardExpiry;
    private Integer CreditCardLimit;
    private Integer CreditCardPin;
    private String DateOfBillGeneration;

    @Override
    public String toString() {
        return "CreditCard{" +
                "CreditCardHolder='" + CreditCardHolder + '\'' +
                ", CreditCardCVV=" + CreditCardCVV +
                ", CreditCardExpiry='" + CreditCardExpiry + '\'' +
                ", CreditCardLimit=" + CreditCardLimit +
                ", CreditCardPin=" + CreditCardPin +
                ", DateOfBillGeneration='" + DateOfBillGeneration + '\'' +
                ", DateOfBillPayment='" + DateOfBillPayment + '\'' +
                ", CreditCardNumber=" + CreditCardNumber +
                '}';
    }

    private String DateOfBillPayment;
    private  Long CreditCardNumber;

    public CreditCard(){

    }

    public String getCreditCardHolder() {
        return CreditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        CreditCardHolder = creditCardHolder;
    }

    public Integer getCreditCardCVV() {
        return CreditCardCVV;
    }

    public void setCreditCardCVV(Integer creditCardCVV) {
        CreditCardCVV = creditCardCVV;
    }

    public String getCreditCardExpiry() {
        return CreditCardExpiry;
    }

    public void setCreditCardExpiry(String creditCardExpiry) {
        CreditCardExpiry = creditCardExpiry;
    }

    public Integer getCreditCardLimit() {
        return CreditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        CreditCardLimit = creditCardLimit;
    }

    public Integer getCreditCardPin() {
        return CreditCardPin;
    }

    public void setCreditCardPin(Integer creditCardPin) {
        CreditCardPin = creditCardPin;
    }

    public String getDateOfBillGeneration() {
        return DateOfBillGeneration;
    }

    public void setDateOfBillGeneration(String dateOfBillGeneration) {
        DateOfBillGeneration = dateOfBillGeneration;
    }

    public String getDateOfBillPayment() {
        return DateOfBillPayment;
    }

    public void setDateOfBillPayment(String dateOfBillPayment) {
        DateOfBillPayment = dateOfBillPayment;
    }

    public Long getCreditCardNumber() {
        return CreditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        CreditCardNumber = creditCardNumber;
    }

    public CreditCard(Long creditCardNumber, String creditCardHolder, Integer creditCardCVV, String creditCardExpiry, Integer creditCardLimit, Integer creditCardPin, String dateOfBillGeneration, String dateOfBillPayment) {
        CreditCardNumber = creditCardNumber;
        CreditCardHolder = creditCardHolder;
        CreditCardCVV = creditCardCVV;
        CreditCardExpiry = creditCardExpiry;
        CreditCardLimit = creditCardLimit;
        CreditCardPin = creditCardPin;
        DateOfBillGeneration = dateOfBillGeneration;
        DateOfBillPayment = dateOfBillPayment;
    }
}
