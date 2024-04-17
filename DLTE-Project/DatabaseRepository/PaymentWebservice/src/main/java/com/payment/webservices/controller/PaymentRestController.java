package com.payment.webservices.controller;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankUsersServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
//    @Operation(summary = "This is to do new transaction")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Payee Inserted successfully"),
//            @ApiResponse(responseCode = "409", description = "Payee account and sender's account cannot be same"),
//            @ApiResponse(responseCode = "204", description = "No record exists"),
//            @ApiResponse(responseCode = "403", description = "Sender account is inactive"),
//            @ApiResponse(responseCode = "500", description = "Internal server error")
//    })
    public ResponseEntity<String> newTransactions(@Valid @RequestBody Transaction transaction){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Customer customer=service.findByUsernameCustomer(username);
        List<Long> senderAccountNumber=service.getAccountNumbersByCustomerId(customer.getCustomerId());
        if (senderAccountNumber.contains(transaction.getTransactionFrom())) {

            Transaction transaction1 = null;
            try {
                transaction1 = paymentTransferRepository.processTransaction(transaction);
                logger.info(resourceBundle.getString("transaction.add") + transaction.getTransactionTo());
                return ResponseEntity.status(HttpStatus.OK).body(resourceBundle.getString("transaction.add") + transaction1.getTransactionTo());
            } catch (PayeeException payeeException) {
                logger.warn(resourceBundle.getString("transaction.fail") + transaction.getTransactionTo());
                String errorMessage = payeeException.getMessage();
                if (errorMessage.equals(resourceBundle.getString("insufficient.balance"))) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                } else if (errorMessage.equals(resourceBundle.getString("no.payee.found"))) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
                } else if (errorMessage.equals(resourceBundle.getString("sender.inactive"))) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
                }else if(errorMessage.equals(resourceBundle.getString("rtgs.minimum.amount"))){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
                }else if(errorMessage.equals(resourceBundle.getString("minimum.balance.fail"))){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
                }
                else {
                    // For any other type of exception, return a generic error response
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
                }
            }
        }else{
            logger.warn(resourceBundle.getString("logger.no.sender.account")+customer.getCustomerId());
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
