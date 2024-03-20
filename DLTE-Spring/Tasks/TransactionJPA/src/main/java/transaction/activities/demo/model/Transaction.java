package transaction.activities.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "Transaction_Table")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "transaction")//auto increment starts from custom value
    @SequenceGenerator(name="transaction",sequenceName = "transaction_seq",initialValue = 12345,allocationSize = 1)
    private long transactionID;
    @Column(name = "Transaction_date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "username")
    private String user;
    @Column(name = "Transaction_amount")
    private double amount;
    @Column(name = "Transaction_balance")
    private double balance;
    @Column(name="Transaction_type")
    private String type;

    public Transaction() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Transaction(long transactionID, Date date, String user, double amount, double balance) {
        this.transactionID = transactionID;
        this.date = date;
        this.user = user;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", transactionID=" + transactionID +
                ", user='" + user + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
