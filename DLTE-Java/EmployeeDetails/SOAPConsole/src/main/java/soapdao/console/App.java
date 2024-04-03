package soapdao.console;

import soapdao.EmployeeException;
import soapdao.implementation.Employee;
import soapdao.implementation.WebServiceDAO;
import soapdao.implementation.WebServiceDAOService;

import javax.xml.ws.BindingProvider;
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
        WebServiceDAOService webServiceDAOService=new WebServiceDAOService();
        WebServiceDAO webServiceDAO=webServiceDAOService.getWebServiceDAOPort();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        InputDetailsCollectAndDisplay inputDetailsCollectAndDisplay=new InputDetailsCollectAndDisplay();
        //System.setProperty("logbackConfiguration","C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\EmployeeDetails\\logback.xml");//or else add log file in resource file
        try {

            while (true) {
//                BindingProvider bp = (BindingProvider) webServiceDAO;
//                Map<String, Object> responseContext = bp.getResponseContext();
//                int statusCode = (int) responseContext.get("javax.xml.ws.http.response.code");
//                System.out.println("HTTP Status Code: " + statusCode);
                System.out.println(resourceBundle.getString("menu.display"));
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        inputDetailsCollectAndDisplay.callCollectDetails();
                        break;
                    case 2:
                        inputDetailsCollectAndDisplay.callDisplayRequired();
                        break;
                    case 3:
                        inputDetailsCollectAndDisplay.callDisplayAll();
                        break;
                    case 4:
                        inputDetailsCollectAndDisplay.callDisplayRequiredPincode();
                        break;
                    case 5:
                       inputDetailsCollectAndDisplay.displayBasedOnPincode();
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println(resourceBundle.getString("invalid.choice"));
                        continue;
                }
            }
        }catch(EmployeeException employeeExceptions){
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
