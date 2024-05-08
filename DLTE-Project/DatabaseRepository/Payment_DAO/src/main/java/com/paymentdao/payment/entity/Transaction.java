package com.paymentdao.payment.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

public class Transaction {

    private Integer transactionId;

    @NotNull(message = "{ERR0001}")
    @Pattern(regexp = "(?i)IMPS|NEFT|RTGS|UPI", message = "{ERR0001}")
    private String transactionType;

    @NotNull
    @Positive(message = "{ERR0002}")
    @Range(min = 100000000000L,max=999999999999L,message = "{ERR0002}")
    private Long transactionFrom;

    @NotNull
    @Positive(message = "{ERR0003}")
    @Range(min = 100000000000L,max=999999999999L,message = "{ERR0003}")
    private Long transactionTo;

    private Date transactionDate;

    @NotNull(message = "{ERR0004}")
    @DecimalMin(value = "1.00",message = "{ERR0004}")
    private Double transactionAmount;


    private String transactionStatus;


    public Transaction() {
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public long getTransactionFrom() {
        return transactionFrom;
    }

    public void setTransactionFrom(long transactionFrom) {
        this.transactionFrom = transactionFrom;
    }

    public long getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(long transactionTo) {
        this.transactionTo = transactionTo;
    }


    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

}
