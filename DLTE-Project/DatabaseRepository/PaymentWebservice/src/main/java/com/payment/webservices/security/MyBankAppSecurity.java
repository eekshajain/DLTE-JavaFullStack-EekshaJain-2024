package com.payment.webservices.security;//package com.payment.webservices.security;

import com.paymentdao.payment.security.MyBankUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyBankAppSecurity {

    @Autowired
    private MyBankUsersServices services;
    AuthenticationManager manager;
    @Autowired
    OfficialsFailureHandler officialsFailureHandler;
    @Autowired
    OfficialsSuccessHandler officialsSuccessHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests().antMatchers("/payeerepo/payee.wsdl").permitAll();
        httpSecurity.formLogin().usernameParameter("username").loginPage("/payment/").failureHandler(officialsFailureHandler).successHandler(officialsSuccessHandler);
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/pictures/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/styles/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/payment/").permitAll();
        httpSecurity.cors();
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.logout().permitAll();


        // 3rd layer
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(services);//calls loadBuUsername
        manager = builder.build();
        httpSecurity.authenticationManager(manager);
        return httpSecurity.build();
    }

}