package springjdbc.transaction.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
@Service
public class TransactionServices {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Transaction apiSave(Transaction transaction){
        int acknowledge=jdbcTemplate.update("insert into transactions_table values(?,?,?,?,?,?)",
                transaction.getTransactionId(),
                transaction.getTransactionDate(),
                transaction.getTransactionBy(),
                transaction.getTransactionTo(),
                transaction.getTransactionAmount(),
                transaction.getTransactionRemarks());
        if(acknowledge!=0) return transaction;
        else return null;
        //return transaction;
    }
    public Transaction newTransactions(Transaction transaction){
        List<Transaction> newList=new ArrayList<>();
        //Transaction transaction=new Transaction(224555L,new Date(2024,02,02),"Arundhathi","Avinash",80000,"Bills");
        int rowsAffected = jdbcTemplate.update(
                "INSERT INTO transactions_table VALUES (?, ?, ?, ?, ?, ?)",
                new Object[] {
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionBy(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionRemarks()
                });
        return transaction;
    }
    public List<Transaction> apiFindBySender(String sender){
        List<Transaction> myCards= (List<Transaction>) jdbcTemplate.query("select * from transactions_table where transaction_by=?",
                new Object[]{sender},
                new BeanPropertyRowMapper<>(Transaction.class));
        return myCards;
    }


    public List<Transaction> apiFindByReceiver(String receiver){
        List<Transaction> myCards= (List<Transaction>) jdbcTemplate.query("select * from transactions_table where transaction_to=?",
                new Object[]{receiver},
                new BeanPropertyRowMapper<>(Transaction.class));
        return myCards;
    }

    public List<Transaction> apiFindByAmount(Integer amount){
        List<Transaction> myCards= (List<Transaction>) jdbcTemplate.query("select * from transactions_table where transaction_amount=?",
                new Object[]{amount},
                new BeanPropertyRowMapper<>(Transaction.class));
        return myCards;
    }

    public Transaction updateTransaction(Transaction transaction){
        int acknowledge=jdbcTemplate.update("update transactions_table set transaction_remarks=? where transaction_id=?",
                new Object[]{transaction.getTransactionRemarks(),transaction.getTransactionId()}
        );
        if(acknowledge!=0) return transaction;
        else  return null;
    }

    public String deleteTransaction(XMLGregorianCalendar startDate, XMLGregorianCalendar endDate){
        java.util.Date startUtilDate = startDate.toGregorianCalendar().getTime();
        java.util.Date endUtilDate = endDate.toGregorianCalendar().getTime();
        int acknowledge= jdbcTemplate.update("delete from transactions_table where transaction_date between ? and ?",
                new Object[]{startUtilDate,endUtilDate}
        );
        if(acknowledge!=0) return "Transaction deleted";
        else return "Failed to delete transaction";
    }

}
