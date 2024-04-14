package com.springboot.webservices.exceptions;

import java.util.ResourceBundle;

public class EmployeeException extends RuntimeException{
    private final String errorMessage;

    public EmployeeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        return resourceBundle.getString(errorMessage);
    }
}
