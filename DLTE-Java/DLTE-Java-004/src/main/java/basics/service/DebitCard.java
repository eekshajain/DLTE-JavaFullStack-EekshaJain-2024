package basics.service;

import java.util.Scanner;

public class DebitCard extends Account{
    protected Long debitCardNumber;
    protected Integer cardPIN;
Scanner scanner=new Scanner(System.in);
    public DebitCard(){

    }

    public DebitCard(Long debitCardNumber, Integer cardPIN) {
        this.debitCardNumber = debitCardNumber;
        this.cardPIN = cardPIN;
    }

    public DebitCard(Long accountNumber, Integer accountBalance, String accountName, Long debitCardNumber, Integer cardPIN) {
        super(accountNumber, accountBalance, accountName);
        this.debitCardNumber = debitCardNumber;
        this.cardPIN = cardPIN;
    }
    public void withdraw(Integer amount){
        if(amount<accountBalance){
            System.out.println("You can withdraw amount");
            System.out.println("Enter your PIN");
            Integer pin=scanner.nextInt();
            if(pin.equals(cardPIN)){
                System.out.println("Withdraw approved");
            }else{
                System.out.println("Withdraw not approved as pin does not match");
            }
        }else{
            System.out.println("Cannot withdraw as amount balance is less");
        }
    }
}
