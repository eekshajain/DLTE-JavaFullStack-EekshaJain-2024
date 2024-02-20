package basics.services;

import lombok.Data;

import java.util.Date;

@Data
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
