package org.example.exceptions;

import java.util.ResourceBundle;

// User defined exception for handling the Withdraw Exception for entering the wrong password
public class WithdrawException extends RuntimeException{
    public WithdrawException() {
        super(String.valueOf(ResourceBundle.getBundle("accounts")));
    }
}
