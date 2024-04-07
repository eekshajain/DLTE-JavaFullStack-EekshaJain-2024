package soapdao.exceptions;

import java.util.ResourceBundle;

public class EmployeeNotFound {
    private final String errorMessage;

    public EmployeeNotFound(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        return resourceBundle.getString(errorMessage);
    }
}
