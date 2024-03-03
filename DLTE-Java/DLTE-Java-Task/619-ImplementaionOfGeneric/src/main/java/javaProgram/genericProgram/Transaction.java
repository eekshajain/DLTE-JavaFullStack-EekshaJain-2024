package java.generic;

import java.util.Date;

public class Transaction {
    private Date dateOfTransaction;
    private Integer amountOfTransaction;

    public Transaction(Date dateOfTransaction, Integer amountOfTransaction, String sentTo, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountOfTransaction = amountOfTransaction;
        this.sentTo = sentTo;
        this.remarks = remarks;
    }

    private String sentTo;
    private String remarks;

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
}
