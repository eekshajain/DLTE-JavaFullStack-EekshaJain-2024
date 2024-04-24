package com.payment.webservices.security;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class OfficialsSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankUsersServices service;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("payment");
    Logger logger= LoggerFactory.getLogger(OfficialsSuccessHandler.class);

    @Override

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Customer myBankUsers= (Customer) authentication.getPrincipal();
        if(myBankUsers.getCustomerStatus().equals("active")){
            if(myBankUsers.getAttempts()>1){
                myBankUsers.setAttempts(1);
                service.updateAttempts(myBankUsers);
            }
            super.setDefaultTargetUrl("/payeerepo/payee.wsdl");
        }
        else{
            logger.warn(resourceBundle.getString("max.attempt"));
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
