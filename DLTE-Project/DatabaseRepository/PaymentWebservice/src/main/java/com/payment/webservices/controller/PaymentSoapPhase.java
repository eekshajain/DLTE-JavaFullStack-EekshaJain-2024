package com.payment.webservices.controller;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.payee.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("com.paymentdao.payment")
public class PaymentSoapPhase {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("payment");
    private final String url = "http://payee.services";
    @Autowired
    public PaymentTransferRepository paymentTransferRepository;
    @Autowired
    MyBankUsersServices service;

    Logger logger = LoggerFactory.getLogger(PaymentSoapPhase.class);

    @PayloadRoot(namespace = url, localPart = "findAllPayeeBasedOnAccountNumberRequest")
    @ResponsePayload      //listing details based on account number
    public FindAllPayeeBasedOnAccountNumberResponse listPayeeBasedOnAccountNumber(@RequestPayload FindAllPayeeBasedOnAccountNumberRequest findAllPayeeBasedOnAccountNumberRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = service.findByUsernameCustomerStream(username);
        List<Long> senderAccountNumber = service.getAccountNumbersByCustomerId(customer.getCustomerId());
        FindAllPayeeBasedOnAccountNumberResponse findAllPayeeBasedOnAccountNumberResponse = new FindAllPayeeBasedOnAccountNumberResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        if (senderAccountNumber.contains(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount())) {
            try {
                List<Payee> payees = new ArrayList<>(); //arraylist of services
                List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferRepository.findAllPayeeBasedOnAccountNumber(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());  //array list of dao fetch all details and store in dao array list
                if (daoPayee != null) {
                    daoPayee.forEach(each -> {    //using lambda expression to copy elements from dao to services
                        Payee currentPayee = new Payee();
                        BeanUtils.copyProperties(each, currentPayee);
                        payees.add(currentPayee);
                    });
                    serviceStatus.setStatus(HttpServletResponse.SC_OK);
                    logger.info(resourceBundle.getString("logger.list.all.account"));
                    if (!payees.isEmpty()) {
                        for (Payee payee : payees) {
                            logger.info("Username: "+username+" Payee details: PayeeId - " + payee.getPayeeId() +
                                    ", Sender Account Number - " + payee.getSenderAccountNumber() +
                                    ", Payee Account Number - " + payee.getPayeeAccountNumber() +
                                    ", Payee Name - " + payee.getPayeeName());
                        }
                    }
                    serviceStatus.setMessage("Payee details for account number " + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
                    findAllPayeeBasedOnAccountNumberResponse.getPayee().addAll(payees); //add all payee object to response
                }
            }
            catch (PayeeException e) {
                logger.warn(resourceBundle.getString("error.message") + e.getMessage());//if no details
                serviceStatus.setStatus(HttpServletResponse.SC_OK);
                serviceStatus.setMessage(resourceBundle.getString("error.one")+e.getMessage());
            }
        }
        findAllPayeeBasedOnAccountNumberResponse.setServiceStatus(serviceStatus); //setting message status in response
        return findAllPayeeBasedOnAccountNumberResponse;
    }

}
