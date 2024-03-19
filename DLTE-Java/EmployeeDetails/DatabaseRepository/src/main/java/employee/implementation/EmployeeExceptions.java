package employee.implementation;

import java.util.List;
import java.util.ResourceBundle;

public class EmployeeExceptions extends RuntimeException{
    public EmployeeExceptions() {
        super(String.valueOf(ResourceBundle.getBundle("application")));
    }

}
