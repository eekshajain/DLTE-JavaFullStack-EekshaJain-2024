package handle.logs;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MobileBanking {
    public static void main(String[] args) {
        Integer amount,billAmount;
        String billName,billType;
        Integer pin;
         Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        GPay gPay=new GPay(542514689L,56000,"Anu",4567,"AnuP");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
 try {
    System.out.println("Enter your name");
    billName = scanner1.nextLine();
    System.out.println("Enter bill amount");
    billAmount = scanner.nextInt();
    System.out.println("Enter bill type");
    billType = scanner.next();
    gPay.payBill(billName, billAmount, billType);
    }catch(MyBankException exception){
    logger.log(Level.INFO,exception.toString());
   }
    }

}
