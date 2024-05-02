package com.paymentdao.payment.configuration;

import com.paymentdao.payment.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlReturnResultSet;

import java.time.temporal.Temporal;
import java.util.List;
import java.util.Scanner;

public class test {
    @Autowired
    JdbcTemplate jdbcTemplate;
   public String fetchName(String username){
   String customer = jdbcTemplate.queryForObject("select CUSTOMER_NAME from MYBANK_APP_CUSTOMER where USERNAME=?",
              new Object[]{username},String.class);
       return customer;
   }

    public static void main(String[] args) {
       String password1="$2a$10$mB32UAxUAhft.zJA6oJ6beSYNMNZIzkDMQyTCePcnKu9d/4cjIXLq";
       String password2="eeksha123";

        System.out.println(password1.equals(password2));
    }
}
