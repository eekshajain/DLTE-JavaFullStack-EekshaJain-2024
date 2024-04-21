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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.payee.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
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
        Customer customer = service.findByUsernameCustomer(username);
        List<Long> senderAccountNumber = service.getAccountNumbersByCustomerId(customer.getCustomerId());
        FindAllPayeeBasedOnAccountNumberResponse findAllPayeeBasedOnAccountNumberResponse = new FindAllPayeeBasedOnAccountNumberResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        if (senderAccountNumber.contains(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount())) {
            try {
                List<Payee> payees = new ArrayList<>(); //arraylist of services
                List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferRepository.findAllPayeeBasedOnAccountNumber(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());  //array list of dao fetch all details and store in dao array list
                //  Iterator<com.paymentdao.payment.entity.Payee> iterator = daoPayee.iterator();
/*
        while (iterator.hasNext()){
            Payee currentPayee=new Payee();
            BeanUtils.copyProperties(iterator.next(),currentPayee);
            payees.add(currentPayee);
        }*/
                if (daoPayee != null) {
                    daoPayee.forEach(each -> {    //using lambda expression to copy elements from dao to services
                        Payee currentPayee = new Payee();
                        BeanUtils.copyProperties(each, currentPayee);
                        payees.add(currentPayee);
                    });
                    serviceStatus.setStatus(HttpServletResponse.SC_OK);
                    logger.info(resourceBundle.getString("logger.list.all.account"));
                    serviceStatus.setMessage("Payee details for account number " + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
                    findAllPayeeBasedOnAccountNumberResponse.getPayee().addAll(payees); //add all payee object to response
                }
            } catch (PayeeException e) {
                logger.warn(resourceBundle.getString("error.message") + e.getMessage());//if no details
                serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
                serviceStatus.setMessage(e.getMessage());
            }
        } else {
            serviceStatus.setStatus(HttpStatus.NOT_FOUND.value());
            serviceStatus.setMessage(resourceBundle.getString("sender.no.account") + " " + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
        }
        findAllPayeeBasedOnAccountNumberResponse.setServiceStatus(serviceStatus); //setting message status in response
        return findAllPayeeBasedOnAccountNumberResponse;
    }


    @PayloadRoot(namespace = url, localPart = "findAllPayeeRequest")
    @ResponsePayload  //list all users
    public FindAllPayeeResponse listAllPayee(@RequestPayload FindAllPayeeRequest findAllPayeeRequest) {

        FindAllPayeeResponse findAllPayeeResponse = new FindAllPayeeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            List<Payee> payees = new ArrayList<>();
            List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferRepository.findAllPayee();
            Iterator<com.paymentdao.payment.entity.Payee> iterator = daoPayee.iterator();
            if (daoPayee != null) {
                while (iterator.hasNext()) {
                    Payee currentPayee = new Payee();
                    BeanUtils.copyProperties(iterator.next(), currentPayee);
                    payees.add(currentPayee);
                }
                serviceStatus.setStatus(HttpServletResponse.SC_OK);//request succeeded normally.
                logger.info(resourceBundle.getString("logger.list.all"));
                serviceStatus.setMessage(resourceBundle.getString("details.fetched"));
                findAllPayeeResponse.getPayee().addAll(payees);
            }
        } catch (PayeeException e) {
            logger.warn(resourceBundle.getString("error.message") + e.getMessage());//if no details
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);//request succeeded but that there was no new information to return.
            serviceStatus.setMessage(e.getMessage());
        }
        findAllPayeeResponse.setServiceStatus(serviceStatus);
        return findAllPayeeResponse;
    }


    @PayloadRoot(namespace = url, localPart = "findAllPayeeReqRequest")
    @ResponsePayload  //list all users
    public FindAllPayeeReqResponse listAllPayee(@RequestPayload FindAllPayeeReqRequest findAllPayeeRequest) {
        FindAllPayeeReqResponse findAllPayeeResponse = new FindAllPayeeReqResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            List<PayeeRequired> payees = new ArrayList<>();
            List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferRepository.findAllPayee();
            Iterator<com.paymentdao.payment.entity.Payee> iterator = daoPayee.iterator();

            while (iterator.hasNext()) {
                PayeeRequired currentPayee = new PayeeRequired();
                BeanUtils.copyProperties(iterator.next(), currentPayee);
                payees.add(currentPayee);
            }
            serviceStatus.setStatus(HttpServletResponse.SC_OK);//request succeeded normally.
            logger.info(resourceBundle.getString("logger.list.all.requiredOnly"));
            serviceStatus.setMessage(resourceBundle.getString("details.fetched"));
            findAllPayeeResponse.getPayeeRequired().addAll(payees);
        } catch (PayeeException e) {
            logger.warn(resourceBundle.getString("error.message") + e.getMessage());
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);//request succeeded but that there was no new information to return.
            serviceStatus.setMessage(e.getMessage());
        }
        findAllPayeeResponse.setServiceStatus(serviceStatus);
        return findAllPayeeResponse;
    }


    @PayloadRoot(namespace = url, localPart = "findAllPayeeBasedOnAccountNumberLambdaRequest")
    @ResponsePayload  //list user based on account number using lambda
    public FindAllPayeeBasedOnAccountNumberLambdaResponse listAllPayeeLambda(@RequestPayload FindAllPayeeBasedOnAccountNumberLambdaRequest findAllPayeeBasedOnAccountNumberLambdaRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer = service.findByUsernameCustomer(username);
        List<Long> senderAccountNumber = service.getAccountNumbersByCustomerId(customer.getCustomerId());

        FindAllPayeeBasedOnAccountNumberLambdaResponse findAllPayeeBasedOnAccountNumberLambdaResponse = new FindAllPayeeBasedOnAccountNumberLambdaResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            // List<Payee> payees = new ArrayList<>();
            List<PayeeRequired> payees = new ArrayList<>();
            List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferRepository.findAllPayee();

//           daoPayee.forEach(daoPayee1->{          //lambda expression to print based on account number
//               if(daoPayee1.getSenderAccountNumber()==findAllPayeeBasedOnAccountNumberLambdaRequest.getSenderAccount()){
//                   Payee currentPayee=new Payee();
//                   BeanUtils.copyProperties(daoPayee1,currentPayee);
//                   payees.add(currentPayee);
//               }
//           });
            daoPayee.stream() //using streams
                    .filter(payee -> payee.getSenderAccountNumber() == findAllPayeeBasedOnAccountNumberLambdaRequest.getSenderAccount())
                    .forEach(payee -> {
                        // Payee currentPayee = new Payee();
                        PayeeRequired currentPayee = new PayeeRequired();
                        BeanUtils.copyProperties(payee, currentPayee);
                        payees.add(currentPayee);
                    });
            if (payees.size() != 0) {
                serviceStatus.setStatus(HttpServletResponse.SC_OK);//request succeeded normally.
                logger.info(resourceBundle.getString("logger.list.all.account.lambda"));
                serviceStatus.setMessage(resourceBundle.getString("details.fetched"));
                findAllPayeeBasedOnAccountNumberLambdaResponse.getPayeeRequired().addAll(payees);
            } else {

                throw new com.payment.webservices.exceptions.PayeeException(resourceBundle.getString("no.payee") + findAllPayeeBasedOnAccountNumberLambdaRequest.getSenderAccount());
            }
        } catch (com.payment.webservices.exceptions.PayeeException e) {
            logger.warn(resourceBundle.getString("error.message") + e.getMessage());
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);//request succeeded but that there was no new information to return.
            serviceStatus.setMessage(e.getMessage());
        }
        findAllPayeeBasedOnAccountNumberLambdaResponse.setServiceStatus(serviceStatus);
        return findAllPayeeBasedOnAccountNumberLambdaResponse;
    }
}
