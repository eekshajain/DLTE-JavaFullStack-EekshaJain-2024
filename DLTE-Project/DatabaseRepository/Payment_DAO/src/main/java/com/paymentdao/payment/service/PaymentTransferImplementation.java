package com.paymentdao.payment.service;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
    public List<Payee> findAllPayee()  {
        List<Payee> payees;
            logger.info(resourceBundle.getString("logger.list.all"));
            payees = jdbcTemplate.query("select * from MYBANK_APP_Payee",  // query to fetch all
//                new BeanPropertyRowMapper<>(Payee.class)
                    new PayeeMapper());

        if(payees.size()==0){
            logger.error(resourceBundle.getString("no.payee"));
            throw new PayeeException(resourceBundle.getString("no.payee"));
        }
        return payees;
    }

    @Override
    public void processTransaction(long senderAccountNumber, long payeeAccountNumber, String transactionType, double transactionAmount) {
        String procedureCall = "CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)";
        try {
            jdbcTemplate.update(procedureCall, senderAccountNumber, payeeAccountNumber, transactionType, transactionAmount);
        }
        catch(DataAccessException dataException){
            if(dataException.getLocalizedMessage().contains("ORA-20001"))
                throw new PayeeException(resourceBundle.getString("insufficient.balance"));
            if(dataException.getLocalizedMessage().contains("ORA-20002"))
                throw new PayeeException(resourceBundle.getString("no.payee.found"));
            if(dataException.getLocalizedMessage().contains("ORA-20003"))
                throw new PayeeException(resourceBundle.getString("sender.inactive"));
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
