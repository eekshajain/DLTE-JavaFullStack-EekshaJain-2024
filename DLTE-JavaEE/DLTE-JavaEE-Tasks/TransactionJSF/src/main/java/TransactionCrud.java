import transaction.jdbctemplate.demo.entity.Transaction;
import transaction.jdbctemplate.demo.services.TransactionServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class TransactionCrud {
    TransactionServices transactionServices=new TransactionServices();
    Transaction transaction=new Transaction();
    List<Transaction> transactionList=new ArrayList<>();

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void callNewTransaction(Transaction transaction1){
   transactionServices.apiSave(transaction1);
    }

   public void callFindBySender(String sender){
        transactionList=transactionServices.apiFindBySender(sender);
   }
}
