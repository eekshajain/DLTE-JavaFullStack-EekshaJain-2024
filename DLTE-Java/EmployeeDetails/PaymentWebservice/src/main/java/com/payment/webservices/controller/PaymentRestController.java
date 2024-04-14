package com.payment.webservices.controller;

import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import javax.validation.Valid;
import java.util.HashMap;
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


    @PostMapping("/new")
    public ResponseEntity<String> newTransactions(@Valid @RequestBody Transaction transaction){
        try{
            paymentTransferRepository.processTransaction(transaction.getTransactionFrom(),transaction.getTransactionTo(),transaction.getTransactionType(),transaction.getTransactionAmount());
            logger.info(resourceBundle.getString("transaction.add")+transaction.getTransactionTo());
            return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("transaction.add")+transaction.getTransactionTo());
           // return ResponseEntity.ok(resourceBundle.getString("transaction.add"));
        }catch (PayeeException payeeException){
            logger.warn(resourceBundle.getString("transaction.fail")+transaction.getTransactionTo());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payeeException.getMessage());
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
