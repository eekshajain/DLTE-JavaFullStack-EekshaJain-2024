package java.generic;

import org.omg.DynamicAny.DynArray;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
       MyBankDatabase<Transaction> transactionMyBankDatabase=new MyBankDatabase<>();
        MyBankDatabase<CreditCard> creditCardMyBankDatabase=new MyBankDatabase<>();

        transactionMyBankDatabase.newObjects=new Transaction[2];

        Transaction transaction1=new Transaction(new Date(2,12,2023),2300,"Spandana","Family");
        Transaction transaction2=new Transaction(new Date(2,1,2024),500,"Divija","Friend");
        System.out.println(transactionMyBankDatabase.insertRecord(transaction1));
    }
}
