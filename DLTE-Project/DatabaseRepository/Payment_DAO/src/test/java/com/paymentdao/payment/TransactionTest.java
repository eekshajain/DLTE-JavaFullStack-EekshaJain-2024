package com.paymentdao.payment;

import com.paymentdao.payment.entity.Transaction;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @Test
    public void testTransactionValidation_ValidTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("NEFT");
        transaction.setTransactionFrom(123456789012L);
        transaction.setTransactionTo(987654321098L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(0, violations.size(), "Valid transaction should have no violations");
    }

    @Test
    public void testTransactionValidation_InvalidTransactionType() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("invalidType");
        transaction.setTransactionFrom(123456789012L);
        transaction.setTransactionTo(987654321098L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(1, violations.size(), "Transaction with invalid type should have one violation");
    }

    @Test
    public void testTransactionValidation_InvalidTransactionFrom() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("neft");
        transaction.setTransactionFrom(12345678012L);
        transaction.setTransactionTo(987654321098L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(1, violations.size(), "Transaction with invalid size of sender account number should have one violation");
    }

    @Test
    public void testTransactionValidation_InvalidTransactionTo() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("rtgs");
        transaction.setTransactionFrom(123456789012L);
        transaction.setTransactionTo(987654328L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(1, violations.size(), "Transaction with invalid size of payee account number should have one violation");
    }


}
