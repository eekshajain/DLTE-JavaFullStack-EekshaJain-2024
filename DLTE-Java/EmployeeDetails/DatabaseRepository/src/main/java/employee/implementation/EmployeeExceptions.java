package employee.implementation;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class EmployeeExceptions extends RuntimeException{
//    public EmployeeExceptions() {
//        super(String.valueOf(ResourceBundle.getBundle("application")));
//    }
private final String errorMessage;

    public EmployeeExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        return resourceBundle.getString(errorMessage);
    }

}
