package transaction.jdbctemplate.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import transaction.jdbctemplate.demo.entity.Transaction;

import java.util.List;

@Service
public class TransactionServices {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Transaction apiSave(Transaction transaction){
        int acknowledge=jdbcTemplate.update("insert into transactions_table values(?,?,?,?,?,?)",
                new Object[]{
                   transaction.getTransactionId(),
                   transaction.getTransactionDate(),
                   transaction.getTransactionBy(),
                   transaction.getTransactionTo(),
                   transaction.getTransactionAmount(),
                   transaction.getTransactionRemarks()
                });
//        if(acknowledge!=0) return transaction;
//        else return "No;
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

}
