package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class TransactionWrapper{
    private List<Transaction> transactions;

    public TransactionWrapper() {
    }

    @XmlElement(name = "transaction")
    public List<Transaction> getTransactions(){
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}