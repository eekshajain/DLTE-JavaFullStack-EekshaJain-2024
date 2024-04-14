package com.payment.webservices.security;//package com.payment.webservices.security;//package springjdbc.transaction.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankUsersServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(OfficialsFailureHandler.class);

    public MyBankUsers signUp(MyBankUsers myBankUsers){
       jdbcTemplate.update("insert into  MYBANK_APP_USERS values(USERS_SEQ.nextval,?,?,?,?,?,?)",new Object[]{
                myBankUsers.getName(),myBankUsers.getUsername(),
                myBankUsers.getPassword(),myBankUsers.getEmail(),
                myBankUsers.getStatus(),
               myBankUsers.getAttempts()
        });
        return myBankUsers;
    }
//    public Customer update(Customer customer){
//        jdbcTemplate.update("update mybank_app_customer set ")
//    }
    public MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from MYBANK_APP_USERS where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }

    public void updateAttempts(MyBankUsers myBankUsers){
        jdbcTemplate.update("update MYBANK_APP_USERS set attempts=? where username=?",
                new Object[]{myBankUsers.getAttempts(),myBankUsers.getUsername()});
      logger.info("Attempts are updated");
    }

    public void updateStatus(MyBankUsers myBankUsers){
        jdbcTemplate.update("update MYBANK_APP_USERS set status=0 where username=?",
                new Object[]{myBankUsers.getUsername()});
        logger.info("Status has changed");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers users = findByUsername(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }
    }

