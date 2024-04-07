package com.paymentdao.payment.exceptions;

public class PayeeException extends  RuntimeException {
    public PayeeException(String message) {
        super(message);
    }
}
