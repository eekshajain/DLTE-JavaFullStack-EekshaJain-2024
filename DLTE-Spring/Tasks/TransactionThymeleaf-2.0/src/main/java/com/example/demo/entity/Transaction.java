package com.example.demo.entity;

import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.Date;

@Component
public class Transaction {
    @NotNull(message ="{transactionId.notNull}")
    @Digits(integer = 3,fraction = 0)
    private Long transactionId;
    @PastOrPresent(message = "{transaction.date}")
    private Date transactionDate;
    @NotBlank(message = "{transactionFrom.notNull}")
    private String transactionBy;
    @NotBlank(message = "{transactionTo.notNull}")
    private String transactionTo;
    @NotNull(message = "{transactionAmount.notnull}")
    @DecimalMin(value = "0.01",message = "{transactionAmount.not.zero}")
    private Integer transactionAmount;
    @NotBlank(message = "{transactionRemarks.notNull}")
    @Pattern(regexp = "(?i)friend|bills|family|general", message = "{transaction.type.pattern}")
    private String transactionRemarks;

    public Transaction(@NotNull(message = "{transactionId.notNull}") @Digits(integer = 3, fraction = 0) Long transactionId, @PastOrPresent(message = "{transaction.date}") Date transactionDate, @NotBlank(message = "{transactionFrom.notNull}") String transactionBy, @NotBlank(message = "{transactionTo.notNull}") String transactionTo, @NotNull(message = "{transactionAmount.notnull}") @DecimalMin(value = "0.01", message = "{transactionAmount.not.zero}") Integer transactionAmount, @NotBlank(message = "{transactionRemarks.notNull}") @Pattern(regexp = "(?i)friend|bills|family|general", message = "{transaction.type.pattern}") String transactionRemarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionBy = transactionBy;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    public Transaction() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }
}
