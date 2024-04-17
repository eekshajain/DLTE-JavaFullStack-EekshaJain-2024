package com.paymentdao.payment.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

public class Transaction {
   // @NotNull(message = "{transactionId.notnull}")
    private Integer transactionId;

    @NotNull(message = "{transactionType.notnull}")
    @Pattern(regexp = "(?i)IMPS|NEFT|RTGS|UPI", message = "{transaction.type.pattern}")
    private String transactionType;

    @NotNull
    @Positive(message = "{transactionAccount.positive}")
    @Range(min = 100000000000L,max=999999999999L,message = "{digits.accountNumber.message}")
    private Long transactionFrom;

    @NotNull
    @Positive(message = "{transactionAccount.positive}")
    @Range(min = 100000000000L,max=999999999999L,message = "{digits.accountNumber.message}")
    private Long transactionTo;

    private Date transactionDate;

    @NotNull(message = "{transactionAmount.notnull}")
   // @DecimalMin(value = "0.01",message = "{transactionAmount.not.zero}")
    private Double transactionAmount;

  //  @NotNull(message = "{transactionStatus.notnull}")
    private String transactionStatus;

    public Transaction(Integer transactionId, String transactionType, @NotNull @Positive(message = "{transactionAccount.positive}") @Range(min = 100000000000L, max = 999999999999L, message = "{digits.accountNumber.message}") Long transactionFrom, @NotNull @Positive(message = "{transactionAccount.positive}") @Range(min = 100000000000L, max = 999999999999L, message = "{digits.accountNumber.message}") Long transactionTo, Date transactionDate, @NotNull Double transactionAmount, String transactionStatus) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionFrom = transactionFrom;
        this.transactionTo = transactionTo;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionStatus = transactionStatus;
    }

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
