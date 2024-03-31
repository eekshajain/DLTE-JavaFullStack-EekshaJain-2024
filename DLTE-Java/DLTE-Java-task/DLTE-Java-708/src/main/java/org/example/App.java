package org.example;

import org.example.middleware.DatabaseTarget;
import org.example.remotes.StorageTarget;
import org.example.services.AccountService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Scanner scanner=new Scanner(System.in);
        StorageTarget storageTarget=new DatabaseTarget();

        AccountService service=new AccountService(storageTarget);
    //    service.callVerifyPassword(scanner.next(),scanner.next());
       // service.callFindByUsername("eeksha25");
        service.callWithdraw("eeksha25","eeksha365",500);
////        CreditCard creditCard=new CreditCard(11111122343L,383,new Date("12/2/2029"),91000,20000,71000,4844,true,"Annapoorna Pai");
//        creditCardServices.callSave(creditCard);
//        System.out.println(creditCardServices.callFindAll());
//        System.out.println(creditCardServices.callFindById(111122343L));
//        System.out.println(creditCardServices.callFindAllByLimit(101000));;
//        CreditCard creditCard=new CreditCard(11111122343L,383,new Date("12/2/2029"),91000,10000,81000,1111,true,"Annapoorna Pai");
////        creditCardServices.callUpdate(creditCard);
//        creditCardServices.callDelete(creditCard);
    }
}

