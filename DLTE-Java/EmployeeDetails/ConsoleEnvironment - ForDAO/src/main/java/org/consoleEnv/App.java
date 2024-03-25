package org.consoleEnv;

import employee.implementation.EmployeeExceptions;
import employee.implementation.ReadAndDisplayUsingDatabase;
import employee.interfaces.InputEmployeeDetails;
import employee.methodsCalling.InputDetailsCollectAndDisplay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        Scanner scanner3=new Scanner(System.in);
        InputEmployeeDetails employeeDetails=null;
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        InputDetailsCollectAndDisplay inputDetailsCollectAndDisplay=new InputDetailsCollectAndDisplay();
        //System.setProperty("logbackConfiguration","C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\EmployeeDetails\\logback.xml");//or else add log file in resource file
        try {
                employeeDetails = new ReadAndDisplayUsingDatabase();
        while (true) {
            System.out.println(resourceBundle.getString("menu.display"));
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    inputDetailsCollectAndDisplay.callCollectDetails(employeeDetails);
                    break;
                case 2:
                    inputDetailsCollectAndDisplay.callDisplayRequired(employeeDetails);
                    break;
                case 3:
                   inputDetailsCollectAndDisplay.callDisplayAll(employeeDetails);
                    break;
                case 4:
                    inputDetailsCollectAndDisplay.callDisplayRequiredPincode(employeeDetails);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println(resourceBundle.getString("invalid.choice"));
                    continue;
            }
        }
            }catch(EmployeeExceptions employeeExceptions){
            System.out.println(employeeExceptions.getErrorMessage());
        } catch (InputMismatchException e){
                System.out.println(resourceBundle.getString("number.only"));
                scanner.next();
            } catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        finally {
                scanner.close();
                scanner1.close();
                scanner2.close();
                scanner3.close();
            }
    }

}
