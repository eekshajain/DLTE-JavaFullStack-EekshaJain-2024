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

@Service
public class MyBankUsersServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(MyBankUsersServices.class);

//    public MyBankUsers signUp(MyBankUsers myBankUsers){
//       jdbcTemplate.update("insert into  MYBANK_APP_USERS values(USERS_SEQ.nextval,?,?,?,?,?,?)",new Object[]{
//                myBankUsers.getName(),myBankUsers.getUsername(),
//                myBankUsers.getPassword(),myBankUsers.getEmail(),
//                myBankUsers.getStatus(),
//               myBankUsers.getAttempts()
//        });
//        return myBankUsers;
//    }

    public Customer signUp(Customer customer){
        jdbcTemplate.update("insert into  MYBANK_APP_CUSTOMER values(CUSTOMERID_SEQ.nextval,?,?,?,?,?,?,?)",new Object[]{
                customer.getCustomerName(),customer.getCustomerAddress(),
                customer.getCustomerContact(),customer.getUsername(),
                customer.getPassword(),customer.getCustomerStatus(),
                customer.getAttempts()
        });
        return customer;
    }

//    public MyBankUsers findByUsername(String username){
//        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from MYBANK_APP_USERS where username=?",
//                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
//        return myBankUsers;
//    }

    public Customer findByUsernameCustomer(String username){
        Customer customer = jdbcTemplate.queryForObject("select * from MYBANK_APP_CUSTOMER where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }
    public List<Customer> findByUsernameCustomer1(){
        List<Customer> customer = jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER",
                new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }
    public Customer findByUsernameCustomerStream(String username) {
        List<Customer> customerList = findByUsernameCustomer1();
        Customer customer = customerList.stream()
                .filter(customer1 -> customer1.getUsername().equals(username)).findFirst().orElse(null);
        return customer;
    }
    public Long getAccountNumberByCustomerId(int customerId) {
        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new PayeeException("No account found for customer ID: " + customerId);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return null;
        }
    }

    public List<Long> getAccountNumbersByCustomerId(int customerId) {
        String sql = "SELECT a.ACCOUNT_NUMBER " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForList(sql, new Object[]{customerId}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new PayeeException("No account found for customer ID: " + customerId);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            return Collections.emptyList(); // Or handle the error in an appropriate way
        }
    }
//    public void updateAttempts(MyBankUsers myBankUsers){
//        jdbcTemplate.update("update MYBANK_APP_USERS set attempts=? where username=?",
//                new Object[]{myBankUsers.getAttempts(),myBankUsers.getUsername()});
//      logger.info("Attempts are updated");
//    }

    public void updateAttempts(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{customer.getAttempts(),customer.getUsername()});
        logger.info("Attempts are updated");
    }

//    public void updateStatus(MyBankUsers myBankUsers){
//        jdbcTemplate.update("update MYBANK_APP_USERS set status=0 where username=?",
//                new Object[]{myBankUsers.getUsername()});
//        logger.info("Status has changed");
//    }

    public void updateStatus(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{customer.getUsername()});
        logger.info("Status has changed");
    }
    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MyBankUsers users = findByUsername(username);
//        if(users==null)
//            throw new UsernameNotFoundException(username);
//        return users;
//    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer users = findByUsernameCustomer(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }
    }

