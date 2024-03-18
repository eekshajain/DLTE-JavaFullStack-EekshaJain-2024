package read;

import org.example.entity.Transaction;

import java.util.List;

public class TransactionGroup {
    private List<Transaction> transactionList;

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "TransactionGroup{" +
                "transactionList=" + transactionList +
                '}';
    }
}
