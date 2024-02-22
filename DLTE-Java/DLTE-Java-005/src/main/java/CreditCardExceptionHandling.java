import handle.logs.MyBankCreditCardException;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditCardExceptionHandling {
   static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
   static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static void main(String[] args) {
        System.out.println(resourceBundle.getString("welcome.message"));
        Scanner scanner=new Scanner(System.in);
        String filterDate="";
        int option,currPIN,wrongLogin=1;
        Integer lowerLimit,upperLimit;
        Long creditCardNumber;
        CreditCard[] myBank={
                new CreditCard(56726253782L,"Eeksha Jain",234,new Date(2026,12,31),40000,1234,new Date(2024,2,12),new Date(2024,2,20)),
                new CreditCard(45265673663L,"Divija",257,new Date(2027,10,31),45000,5678,new Date(2024,1,10),new Date(2024,1,18)),
                new CreditCard(12345678901L,"Ankitha",782,new Date(2025,7,25),50000,2390,new Date(2024,1,5),new Date(2024,1,28)),
                new CreditCard(45627637820L,"Annapoorna",943,new Date(2025,6,10),55000,6348,new Date(2024,2,20),new Date(2024,2,28)),
                new CreditCard(35265762811L,"Arundhathi",836,new Date(2027,11,20),60000,2846,new Date(2024,2,15),new Date(2024,2,23)),
                new CreditCard(97377635572L,"Akshira",356,new Date(2025,7,10),65000,7543,new Date(2024,1,27),new Date(2024,2,4)),
        };
        CreditCardExceptionHandling analysis= new CreditCardExceptionHandling();
        System.out.println("Enter \n1.Filter based on Limit Amount\n2.Filter based on date of bill payment\n");
        option=scanner.nextInt();
        switch(option){
            case 1:
                try {
                    System.out.println(resourceBundle.getString("lowerlimit.input"));
                    lowerLimit = scanner.nextInt();
                    System.out.println(resourceBundle.getString("upperlmit.input"));
                    upperLimit = scanner.nextInt();
                    analysis.filterBasedOnLimit(myBank, lowerLimit, upperLimit);
                }catch(MyBankCreditCardException exception){
                    System.out.println("No users");
                  logger.log(Level.INFO,exception.getMessage());
                }
                break;
            case 2:
                try {
                    System.out.println(resourceBundle.getString("date.input"));
                    filterDate = scanner.next();
                    analysis.filterBasedOnBillPayment(myBank, filterDate);
                }catch (MyBankCreditCardException exception){
                    System.out.println("No users");
                    logger.log(Level.INFO,exception.getMessage());
                }
                break;
        }
        scanner.close();
    }
    public void filterBasedOnLimit(CreditCard[] customers,Integer start,Integer end){
        logger.log(Level.INFO,"Listing the users");
        System.out.println("List of users having amount limit between "+start+" and "+end);
        int flag=0;
        for(CreditCard each:customers){
            if(each.getCreditCardLimit()>=start && each.getCreditCardLimit()<end){
                System.out.println("User "+each.getCreditCardHolder()+" with card number "+each.getCreditCardNumber()+".");
                flag=1;
            }
        }if(flag==0) {
               throw new MyBankCreditCardException();
        }
    }

    public void filterBasedOnBillPayment(CreditCard[] customers,String date){
        String splitDate[]=date.split("-");
        int flag=0;
        for(CreditCard each:customers){
            if(Integer.parseInt(splitDate[0])==each.getDateOfBillPayment().getDate() && Integer.parseInt(splitDate[1])==each.getDateOfBillPayment().getMonth() && Integer.parseInt(splitDate[2])==each.getDateOfBillPayment().getYear()){
                System.out.println(each.getCreditCardHolder()+" "+each.getCreditCardNumber());
                flag=1;
            }
        }if(flag==0) {
            throw new MyBankCreditCardException();
        }
    }


}
