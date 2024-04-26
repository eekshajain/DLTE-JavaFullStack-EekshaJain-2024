package com.example.demo.security;//package transaction.jdbctemplate.demo.security;//package springjdbc.transaction.demo.security;

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

    public MyBankUsers signUp(MyBankUsers myBankUsers){
       jdbcTemplate.update("insert into  mybankusers values(?,?,?,?,?,?,?)",new Object[]{
                myBankUsers.getName(),myBankUsers.getUsername(),
                myBankUsers.getPassword(),myBankUsers.getEmail(),
                myBankUsers.getContact(),myBankUsers.getAadhaar(),
               myBankUsers.getRole()
        });
        return myBankUsers;
    }

    public MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from mybankusers where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers users = findByUsername(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }
    }

