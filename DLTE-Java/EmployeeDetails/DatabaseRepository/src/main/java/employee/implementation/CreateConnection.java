package employee.implementation;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateConnection {
    static Connection connection;
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    public static Connection createConnection( )  {
        try {
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),
                    resourceBundle.getString("db.user"),
                    resourceBundle.getString("db.password"));
        } catch (SQLException e) {
           throw new EmployeeExceptions("system.error");
        }
        return connection;
    }

}
