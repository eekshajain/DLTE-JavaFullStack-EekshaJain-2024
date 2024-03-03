package java.generic;

import java.util.Date;

public class CreditCard {
    private String CreditCardHolder;
    private Integer CreditCardCVV;
    private Date CreditCardExpiry;
    private Integer CreditCardLimit;
    private Integer CreditCardPin;
    private Date DateOfBillGeneration;
    private Date DateOfBillPayment;
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

    public Date getCreditCardExpiry() {
        return CreditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
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

    public Date getDateOfBillGeneration() {
        return DateOfBillGeneration;
    }

    public void setDateOfBillGeneration(Date dateOfBillGeneration) {
        DateOfBillGeneration = dateOfBillGeneration;
    }

    public Date getDateOfBillPayment() {
        return DateOfBillPayment;
    }

    public void setDateOfBillPayment(Date dateOfBillPayment) {
        DateOfBillPayment = dateOfBillPayment;
    }

    public Long getCreditCardNumber() {
        return CreditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        CreditCardNumber = creditCardNumber;
    }

    public CreditCard(Long creditCardNumber, String creditCardHolder, Integer creditCardCVV, Date creditCardExpiry, Integer creditCardLimit, Integer creditCardPin, Date dateOfBillGeneration, Date dateOfBillPayment) {
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
