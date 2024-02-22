package handle.logs;

import java.util.Scanner;

public class GPay extends Account{
    protected Integer upiPIN;
    protected String userName;

    public GPay(Integer upiPIN, String userName) {
        this.upiPIN = upiPIN;
        this.userName = userName;
    }

    public GPay(Long accountNumber, Integer accountBalance, String accountName, Integer upiPIN, String userName) {
        super(accountNumber, accountBalance, accountName);
        this.upiPIN = upiPIN;
        this.userName = userName;
    }
    public void payBill(String name,Integer amount,String type){
        Scanner scanner=new Scanner(System.in);
        Integer pin;
        int attempts=0;
        while(attempts<5) {
            System.out.println("Enter UPI PIN");
            pin = scanner.nextInt();
            if (pin.equals(upiPIN)) {
                System.out.println(name + " your " + type + " bill of amount " + amount + " Rs. is paid. ");
                break;
            } else {
                attempts++;
            }
        }
        if(attempts>=5) throw new MyBankException();
    }
}
