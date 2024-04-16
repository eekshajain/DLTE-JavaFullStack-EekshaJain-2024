package com.payment.webservices.security;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.security.MyBankUsers;
import com.paymentdao.payment.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OfficialsFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    MyBankUsersServices service;

    Logger logger= LoggerFactory.getLogger(OfficialsFailureHandler.class);

    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        String username = request.getParameter("username");
//        MyBankUsers myBankUsers = service.findByUsername(username);
//        if(myBankUsers!=null){
//            if(myBankUsers.getStatus()!=0){
//                if(myBankUsers.getAttempts()< myBankUsers.getMaxAttempts()){
//                    myBankUsers.setAttempts(myBankUsers.getAttempts()+1);
//                    service.updateAttempts(myBankUsers);
//                    logger.warn("Invalid credentials and attempts taken");
//                    exception=new LockedException("Attempts are taken");
//                }
//                else{
//                    service.updateStatus(myBankUsers);
//                    exception=new LockedException("Max Attempts reached account is suspended");
//                }
//            }
//            else{
//                logger.warn("Account suspended contact admin to redeem");
//            }
//        }
//        super.setDefaultFailureUrl("/login?error=true");
//        super.onAuthenticationFailure(request, response, exception);
//    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        Customer myBankUsers = service.findByUsernameCustomer(username);
        if(myBankUsers!=null){
            if(myBankUsers.getCustomerStatus().equals("active")){
                if(myBankUsers.getAttempts()< myBankUsers.getMaxAttempts()){
                    myBankUsers.setAttempts(myBankUsers.getAttempts()+1);
                    service.updateAttempts(myBankUsers);
                    logger.warn("Invalid credentials and attempts taken");
                    exception=new LockedException("Attempts are taken");
                }
                else{
                    service.updateStatus(myBankUsers);
                    exception=new LockedException("Max Attempts reached account is suspended");
                }
            }
            else{
                logger.warn("Account suspended contact admin to redeem");
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}
