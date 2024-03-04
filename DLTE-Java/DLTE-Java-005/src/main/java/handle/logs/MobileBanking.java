package handle.logs;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MobileBanking {
    public static void main(String[] args) {
        Integer amount, billAmount;
        String billName, billType;
        Integer pin;
        int attempts = 0;
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        GPay gPay = new GPay(542514689L, 56000, "Anu", 4567, "AnuP");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        while (attempts < 5) {
            try {
                System.out.println("Enter your name");
                billName = scanner1.nextLine();
                System.out.println("Enter bill amount");
                billAmount = scanner.nextInt();
                System.out.println("Enter bill type");
                billType = scanner.next();
                System.out.println("Enter UPI PIN");
                pin = scanner.nextInt();
                gPay.payBill(billName, billAmount, billType, pin);
            } catch (MyBankException exception) {
                attempts++;
                System.out.println("You have entered wrong password.You have "+(5-attempts)+" more attempts");
                logger.log(Level.INFO, exception.toString());
                if (attempts >= 5) {
                    System.out.println("Your account is blocked!");
                      logger.log(Level.SEVERE,"Yor account is blocked");
                      break;
                }
            }
        }
    }
}
