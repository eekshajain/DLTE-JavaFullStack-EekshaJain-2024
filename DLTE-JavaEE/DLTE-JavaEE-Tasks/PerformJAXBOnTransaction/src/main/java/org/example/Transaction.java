package org.example;

import java.util.Date;

public class Transaction {
    private String user;
    private Date dateOfTransaction;
    private Integer amountOfTransaction;
    private String sentTo;

    public Transaction() {
    }

    public Transaction(String user, Date dateOfTransaction, Integer amountOfTransaction, String sentTo, String remarks) {
        this.user = user;
        this.dateOfTransaction = dateOfTransaction;
        this.amountOfTransaction = amountOfTransaction;
        this.sentTo = sentTo;
        this.remarks = remarks;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String remarks;

    public Transaction(Date dateOfTransaction, Integer amountOfTransaction, String sentTo, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountOfTransaction = amountOfTransaction;
        this.sentTo = sentTo;
        this.remarks = remarks;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Integer getAmountOfTransaction() {
        return amountOfTransaction;
    }

    public void setAmountOfTransaction(Integer amountOfTransaction) {
        this.amountOfTransaction = amountOfTransaction;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", amountOfTransaction=" + amountOfTransaction +
                ", sentTo='" + sentTo + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
