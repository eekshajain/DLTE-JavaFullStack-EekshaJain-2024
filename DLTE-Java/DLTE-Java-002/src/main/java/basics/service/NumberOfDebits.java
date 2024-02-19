package basics.service;

import java.util.Scanner;

public class NumberOfDebits {
    public static void main(String[] args) {
        int debitCount=0;
        double amountInAccount=5000,afterTransaction;//assume that there is 5000 in account
        Scanner scanner=new Scanner(System.in);
       for(int index=0;index<10;index++){
           System.out.println("Enter amount in account after transaction");
           afterTransaction=scanner.nextDouble();
           if(amountInAccount>afterTransaction){
               debitCount+=1;
           }
           amountInAccount=afterTransaction;
       }
        System.out.println("Number of debits is "+debitCount);
    }
}
