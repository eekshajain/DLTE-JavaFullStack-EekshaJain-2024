package com.payment.webservices.security;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.exceptions.PayeeException;
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
import java.util.ResourceBundle;

@Component
public class OfficialsFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    MyBankUsersServices service;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("payment");
    Logger logger= LoggerFactory.getLogger(OfficialsFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        Customer myBankUsers = null;
        try {
             myBankUsers = service.findByUsernameCustomerStream(username);
        }catch (PayeeException payeeException){
            System.out.println("no");
    }
            if (myBankUsers != null) {
                if (myBankUsers.getCustomerStatus().equals("active")) {
                    if (myBankUsers.getAttempts() < myBankUsers.getMaxAttempts()) {
                        myBankUsers.setAttempts(myBankUsers.getAttempts() + 1);
                        service.updateAttempts(myBankUsers);
                        logger.warn(resourceBundle.getString("logger.invalid.credential"));
                        exception = new LockedException(resourceBundle.getString("attempt.taken"));
                    } else {
                        service.updateStatus(myBankUsers);
                        exception = new LockedException(resourceBundle.getString("max.attempt"));
                    }
                } else {
                    logger.warn(resourceBundle.getString("logger.account.suspend"));
                }

            }else{
                super.setDefaultFailureUrl("/login?error=true");
                super.onAuthenticationFailure(request, response, exception);
            }
    }
}
