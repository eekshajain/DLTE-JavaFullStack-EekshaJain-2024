package org.example;

import java.util.ArrayList;
import java.util.Date;

import static org.example.MyBank.loans;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {

  loans.add(new Loan(1234567L,230000,new Date("2/12/2024"),"open","Eeksha",9880772616L));
  loans.add(new Loan(1234568L,300000,new Date("2/1/2024"),"closed","Deeksha",9564334677L));
  loans.add(new Loan(1234569L,250000,new Date("3/17/2024"),"open","Sameeksha",9876543210L));
  loans.add(new Loan(1234570L,400000,new Date("2/20/2024"),"closed","Veeksha",9765313466L));
  MyBank myBank=((start,end)->{
     for(Loan loan:loans){
         if(loan.getLoanDate().before(end) && loan.getLoanDate().after(start)){
             System.out.println(loan);
         }
     }
  });
myBank.filterBasedDates(new Date("2/13/2024"),new Date("3/25/2024"));

    }


}
