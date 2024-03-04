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
    public boolean payBill(String name,Integer amount,String type,Integer pin){
            if (pin.equals(upiPIN)) {
                System.out.println(name + " your " + type + " bill of amount " + amount + " Rs. is paid. ");
                return true;
            } else {
               throw new MyBankException();
            }

        //if(attempts>=5) throw new MyBankException();
    }
}
