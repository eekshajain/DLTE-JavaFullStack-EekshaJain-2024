package basics.service;
import java.util.Scanner;
public class TaxSlabs {
    public static void main(String[] args) {
        double salary=0;
        double taxOldRegime=0,taxNewRegime=0;
        System.out.println("--------Welcome to my bank--------");
        Scanner scanner=new Scanner(System.in);
        System.out.println("tax to be paid for both regimes");
        System.out.println("Enter your salary: ");
        salary=scanner.nextDouble();
        if(salary>0&& salary<250000){
            System.out.println("Exempt in both the regimes");
        }else if(salary>=250000 && salary<500000){
            taxOldRegime=salary*0.05;
            taxNewRegime=salary*0.05;
            System.out.println("5% of salary to be paid under old regime for range 2.5 lakh to 5 lakh.Amount to be paid is "+taxOldRegime);
            System.out.println("5% of salary to be paid under new regime for range 2.5 lakh to 5 lakh.Amount to be paid is "+taxNewRegime);
        }else if(salary>=500000 && salary<750000){
            taxOldRegime=salary*0.2;
            taxNewRegime=salary*0.1;
            System.out.println("20% of salary to be paid under old regime for range 5 lakh to 7.5 lakh.Amount to be paid is "+taxOldRegime);
            System.out.println("10% of salary to be paid under new regime for range 5 lakh to 7.5 lakh.Amount to be paid is "+taxNewRegime);
        }else if(salary>=750000 && salary<1000000){
            taxOldRegime=salary*0.2;
            taxNewRegime=salary*0.15;
            System.out.println("20% of salary to be paid under old regime for range 7.5 lakh to 10 lakh.Amount to be paid is "+taxOldRegime);
            System.out.println("15% of salary to be paid under new regime for range 7.5 lakh to 10 lakh.Amount to be paid is "+taxNewRegime);
        }else if(salary>=1000000 && salary<1250000){
            taxOldRegime=salary*0.3;
            taxNewRegime=salary*0.2;
            System.out.println("30% of salary to be paid under old regime for range 10 lakh to 12.5 lakh.Amount to be paid is "+taxOldRegime);
            System.out.println("20% of salary to be paid under new regime for range 10 lakh to 12.5 lakh.Amount to be paid is "+taxNewRegime);
        }else if(salary>=1250000 && salary<1500000){
            taxOldRegime=salary*0.3;
            taxNewRegime=salary*0.25;
            System.out.println("30% of salary to be paid under old regime for range 12.5 lakh to 15 lakh.Amount to be paid is "+taxOldRegime);
            System.out.println("25% of salary to be paid under new regime for range 12.5 lakh to 15 lakh.Amount to be paid is "+taxNewRegime);
        }else{
            taxOldRegime=salary*0.3;
            taxNewRegime=salary*0.3;
            System.out.println("30% of salary to be paid under old regime for above 15 lakh.Amount to be paid is "+taxOldRegime);
            System.out.println("30% of salary to be paid under new regime for above 15 lakh.Amount to be paid is "+taxNewRegime);
        }
        scanner.close();
    }
}
