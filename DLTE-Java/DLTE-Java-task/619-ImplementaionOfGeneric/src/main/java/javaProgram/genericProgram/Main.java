package javaProgram.genericProgram;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
       MyBankDatabase<Transaction> transactionMyBankDatabase=new MyBankDatabase<>();
        MyBankDatabase<CreditCard> creditCardMyBankDatabase=new MyBankDatabase<>();

        transactionMyBankDatabase.newObjects=new Transaction[3];
        creditCardMyBankDatabase.newObjects=new CreditCard[3];
        Transaction transaction1=new Transaction(new Date("12/2/2023"),2300,"Spandana","Family");
        Transaction transaction2=new Transaction(new Date("2/1/2024"),500,"Divija","Friend");
        CreditCard creditCard1=new CreditCard(123456789L,"Diya",123,new Date("10/5/2027"),50000,2356,new Date("12/12/2023"),new Date("12/25/2023"));
        CreditCard creditCard2=new CreditCard(987654321L,"Sudha",456,new Date("12/10/2029"),75000,7835,new Date("1/12/2024"),new Date("1/25/2024"));
        //Transaction Generic
        System.out.println(transactionMyBankDatabase.insertRecord(transaction1));
        System.out.println(transactionMyBankDatabase.insertRecord(transaction2));
        System.out.println(transactionMyBankDatabase.read(1));
        transactionMyBankDatabase.update(1,new Transaction(new Date("2/1/2024"),800,"Divija","Friend"));
        System.out.println(transactionMyBankDatabase.read(1));
        System.out.println(transactionMyBankDatabase.delete(1));
        //CreditCard Generic
        System.out.println(creditCardMyBankDatabase.insertRecord(creditCard1));
        System.out.println(creditCardMyBankDatabase.insertRecord(creditCard2));
        System.out.println(creditCardMyBankDatabase.read(0));
        creditCardMyBankDatabase.update(0,new CreditCard(123456789L,"Diya",123,new Date("10/5/2027"),60000,2356,new Date("12/12/2023"),new Date("12/25/2023")));
        System.out.println(creditCardMyBankDatabase.read(0));
        System.out.println(creditCardMyBankDatabase.delete(0));
    }
}
