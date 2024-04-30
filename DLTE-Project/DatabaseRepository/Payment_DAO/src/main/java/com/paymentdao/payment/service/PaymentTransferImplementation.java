package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.exceptions.TransactionException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ResourceBundle;

@Service
public class PaymentTransferImplementation  implements PaymentTransferRepository {
    ResourceBundle resourceBundle= ResourceBundle.getBundle("payment");
    Logger logger = LoggerFactory.getLogger(PaymentTransferImplementation.class);
@Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Payee> findAllPayeeBasedOnAccountNumber(Long accountNumber)  {
        List<Payee> payees;

            logger.info(resourceBundle.getString("logger.account.number")+accountNumber);
             payees = jdbcTemplate.query("select * from MYBANK_APP_Payee where sender_account_number=?", //to fetch based on account number
                    new Object[]{accountNumber},
//                new BeanPropertyRowMapper<>(Payee.class)
                    new PayeeMapper());

        if(payees.size()==0){
            logger.error(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee")+accountNumber);
        }
        return payees;
    }


    @Override
    public Transaction processTransaction(Transaction transaction) {
        String procedureCall = "CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)";
        logger.info(resourceBundle.getString("logger.transaction.initiate")+transaction.getTransactionTo());
        try {
            int rowAffected=jdbcTemplate.update(procedureCall,
                    new Object[]{
                            transaction.getTransactionFrom(),
                            transaction.getTransactionTo(),
                            transaction.getTransactionType(),
                            transaction.getTransactionAmount()
                    }
                    );
            if(rowAffected>0)  logger.info(resourceBundle.getString("logger.transaction.done"));
            return transaction;
        }
        catch(DataAccessException dataException){
            if(dataException.getLocalizedMessage().contains("ORA-20001")) {
                logger.warn(resourceBundle.getString("logger.transaction.fail")+resourceBundle.getString("minimum.balance.fail"));
                throw new TransactionException(resourceBundle.getString("minimum.balance.fail"));
            }
            else if(dataException.getLocalizedMessage().contains("ORA-20002")) {
                logger.warn(resourceBundle.getString("logger.transaction.fail")+resourceBundle.getString("no.payee.found"));
                throw new TransactionException(resourceBundle.getString("no.payee.found"));
            }
            else if(dataException.getLocalizedMessage().contains("ORA-20004")) {
                logger.warn(resourceBundle.getString("logger.transaction.fail")+resourceBundle.getString("sender.inactive"));
                throw new TransactionException(resourceBundle.getString("sender.inactive"));
            }else if(dataException.getLocalizedMessage().contains("ORA-20003")){
                logger.warn(resourceBundle.getString("logger.transaction.fail")+resourceBundle.getString("rtgs.minimum.amount"));
                throw new TransactionException(resourceBundle.getString("rtgs.minimum.amount"));
            }
            else {
                // For any other type of exception, return a generic error response
               throw new TransactionException(resourceBundle.getString("internal.server.error"));
            }
        }
    }


    public class PayeeMapper implements RowMapper<Payee> {
        @Override
        public Payee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Payee payee=new Payee();
            payee.setPayeeId(rs.getInt(1));//mapping based on column index
            payee.setSenderAccountNumber(rs.getLong(2));
            payee.setPayeeAccountNumber(rs.getLong("payee_account_number"));//mapping based on column label
            payee.setPayeeName(rs.getString("payee_name"));
            return payee;
        }
    }
}
