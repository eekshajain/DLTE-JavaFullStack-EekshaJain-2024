package com.payment.webservices.security;//package com.payment.webservices.security;

import com.paymentdao.payment.security.MyBankUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.function.Predicate;

@Configuration
public class TransactionSecurity {

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

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(services)
//                .passwordEncoder(passwordEncoder());
//    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.formLogin().usernameParameter("username").failureHandler(officialsFailureHandler).successHandler(officialsSuccessHandler);
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();


        httpSecurity.authorizeRequests().anyRequest().authenticated();
        // 3rd layer
        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(services);
        manager = builder.build();
        httpSecurity.authenticationManager(manager);
        return httpSecurity.build();
    }

}