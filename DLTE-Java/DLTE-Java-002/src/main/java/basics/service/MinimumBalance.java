package basics.service;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumBalance {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        double availBalance[]=new double[5];
        System.out.println("Enter balance amount of 20 people");
        for(int index=0;index<availBalance.length;index++){
            System.out.println("Enter balance amount of "+(index+1)+" person");
            availBalance[index]=scanner.nextDouble();
        }
        System.out.println("Amount in account before applying charges");
        System.out.println(Arrays.toString(availBalance));
        MinimumBalance.UpdateArray(availBalance);
        System.out.println("Amount in account after applying charges");
        System.out.println(Arrays.toString(availBalance));
    }
    public static void UpdateArray(double balance[]){
        for(int index=0;index<balance.length;index++){
            if(balance[index]>=5000 && balance[index]<10000){
                balance[index]=balance[index]-(balance[index]*0.03);
            }else if(balance[index]>=1000 && balance[index]<5000){
                balance[index]=balance[index]-(balance[index]*0.05);
            }
        }
    }
}
