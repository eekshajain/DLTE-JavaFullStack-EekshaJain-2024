package com.payment.webservices.controller;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.exceptions.TransactionException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankUsersServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@RestController
@ComponentScan("com.paymentdao.payment")
@RequestMapping("/transactions")
public class PaymentRestController {
    Logger logger= LoggerFactory.getLogger(PaymentRestController.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("payment");
    @Autowired
    PaymentTransferRepository paymentTransferRepository;
    @Autowired
    MyBankUsersServices service;

    @PostMapping("/new")
    public ResponseEntity<String> newTransactions(@Valid @RequestBody Transaction transaction){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();//get logged in users username
        Customer customer=service.findByUsernameCustomerStream(username);
        List<Long> senderAccountNumber=service.getAccountNumbersByCustomerId(customer.getCustomerId());//get users account number(1 user might have multiple accounts)
        if (senderAccountNumber.contains(transaction.getTransactionFrom())) {
            Transaction transaction1 = null;
            try {
                transaction1 = paymentTransferRepository.processTransaction(transaction);
                logger.info(resourceBundle.getString("transaction.add") + transaction.getTransactionTo());
                return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("transaction.add") + transaction1.getTransactionTo());
            } catch (TransactionException transactionException) {
                logger.warn(resourceBundle.getString("transaction.fail") + transaction.getTransactionTo());
                String errorMessage = transactionException.getMessage();
                if (errorMessage.equals(resourceBundle.getString("minimum.balance.fail"))) {  //if users balance will fall below minimum balance
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
                } else if (errorMessage.equals(resourceBundle.getString("no.payee.found"))) { //if user does not have particular payee
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
                } else if (errorMessage.equals(resourceBundle.getString("sender.inactive"))) { //if users account is inactive
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
                }else if(errorMessage.equals(resourceBundle.getString("rtgs.minimum.amount"))){ //if rtgs amount to be sent is less than 50000
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
                }
                else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
                }
            }
        }else{
            logger.warn(resourceBundle.getString("logger.no.sender.account")+customer.getCustomerId());//if sender doesnot ave account
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("sender.no.account"));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}

