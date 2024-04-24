package com.paymentdao.payment.security;//package com.payment.webservices.security;//package springjdbc.transaction.demo.security;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.exceptions.PayeeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class MyBankUsersServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(MyBankUsersServices.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("payment");

    public Customer signUp(Customer customer){
        jdbcTemplate.update("insert into  MYBANK_APP_CUSTOMER values(CUSTOMERID_SEQ.nextval,?,?,?,?,?,?,?)",new Object[]{
                customer.getCustomerName(),customer.getCustomerAddress(),
                customer.getCustomerContact(),customer.getUsername(),
                customer.getPassword(),customer.getCustomerStatus(),
                customer.getAttempts()
        });
        return customer;
    }

    public List<Customer> findAllCustomer(){
        List<Customer> customer = jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER",
                new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }
    public Customer findByUsernameCustomerStream(String username) {
        List<Customer> customerList = findAllCustomer();
        Customer customer = customerList.stream()
                .filter(customer1 -> customer1.getUsername().equals(username)).findFirst().orElse(null);
        if(customer==null){
            logger.warn(resourceBundle.getString("logger.no.username"));
            throw new PayeeException(resourceBundle.getString("no.username"));
        }
        return customer;
    }

    public List<Long> getAccountNumbersByCustomerId(int customerId) {
        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForList(sql, new Object[]{customerId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new PayeeException(resourceBundle.getString("no.account") + customerId);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return Collections.emptyList(); // Or handle the error in an appropriate way
        }
    }


    public void updateAttempts(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{customer.getAttempts(),customer.getUsername()});
        logger.info(resourceBundle.getString("logger.attempt.update"));
    }



    public void updateStatus(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{customer.getUsername()});
        logger.info(resourceBundle.getString("logger.status.update"));
    }
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer users = findByUsernameCustomerStream(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
           // throw new PayeeException(resourceBundle.getString("no.username"));
        return users;
    }
    }

