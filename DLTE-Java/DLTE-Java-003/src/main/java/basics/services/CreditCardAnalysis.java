package basics.services;

import java.util.Date;
import java.util.Scanner;

public class CreditCardAnalysis {

    public static void main(String[] args) {
        System.out.println("--------Welcome to MyBank--------");
        Scanner scanner=new Scanner(System.in);
        String filterDate="";
        int option,currPIN,wrongLogin=1;
        Integer lowerLimit,upperLimit;
        Long creditCardNumber;
        CreditCard[] myBank={
                new CreditCard(56726253782L,"Eeksha Jain",234,new Date(2026,12,31),40000,1234,new Date(2024,2,12),new Date(2024,2,20)),
                new CreditCard(45265673663L,"Divija",257,new Date(2027,10,31),45000,5678,new Date(2024,1,10),new Date(2024,1,18)),
                new CreditCard(12345678901L,"Ankitha",782,new Date(2025,7,25),50000,2390,new Date(2024,1,5),new Date(2024,1,28)),
                new CreditCard(45627637820L,"Annapoorna",943,new Date(2025,6,10),55000,6348,new Date(2024,2,20),new Date(2024,2,28)),
                new CreditCard(35265762811L,"Arundhathi",836,new Date(2027,11,20),60000,2846,new Date(2024,2,15),new Date(2024,2,23)),
                new CreditCard(97377635572L,"Akshira",356,new Date(2025,7,10),65000,7543,new Date(2024,1,27),new Date(2024,2,4)),
        };
        CreditCardAnalysis analysis= new CreditCardAnalysis();
        System.out.println("Enter \n1.Filter based on Limit Amount\n2.Filter based on date of bill payment\n3.Update your PIN\n4.Update Amount limit of those whose bill generation date is on 5");
        option=scanner.nextInt();
        switch(option){
            case 1:
                System.out.println("Enter Lower Limit");
                lowerLimit=scanner.nextInt();
                System.out.println("Enter upper Limit");
                upperLimit=scanner.nextInt();
                analysis.filterBasedOnLimit(myBank,lowerLimit,upperLimit);
                break;
            case 2:
                System.out.println("Enter Date(dd-mm-yyyy)");
                filterDate=scanner.next();
                analysis.filterBasedOnBillPayment(myBank,filterDate);
                break;
            case 3:
                System.out.println("Enter your credit card number");
                creditCardNumber=scanner.nextLong();
                System.out.println("Enter your Current PIN");
                currPIN=scanner.nextInt();
                for(CreditCard each:myBank){
                    if(each.getCreditCardNumber().equals(creditCardNumber) && each.getCreditCardPin()==currPIN){
                        analysis.updatePIN(myBank,creditCardNumber);
                        wrongLogin=0;
                    }
                }if(wrongLogin==1) System.out.println("Data given are incorrect");
                break;
            case 4:for(CreditCard each:myBank){
                    if(each.getDateOfBillGeneration().getDate()==5){
                        int extendLimit=each.getCreditCardLimit();
                        extendLimit+=(extendLimit*0.5);
                        each.setCreditCardLimit(extendLimit);
                        System.out.println(each.getCreditCardHolder()+",Congratulations!Your Card limit is updated!After updation your limit is "+extendLimit);
                    }

            }
        }

    }
    public void filterBasedOnLimit(CreditCard[] customers,Integer start,Integer end){
        System.out.println("List of users having amount limit between "+start+" and "+end);
        for(CreditCard each:customers){
            if(each.getCreditCardLimit()>=start && each.getCreditCardLimit()<end){
                System.out.println("User "+each.getCreditCardHolder()+" with card number "+each.getCreditCardNumber()+".");
            }
        }
    }

    public void filterBasedOnBillPayment(CreditCard[] customers,String date){
        String splitDate[]=date.split("-");
        for(CreditCard each:customers){
            if(Integer.parseInt(splitDate[0])==each.getDateOfBillGeneration().getDate() && Integer.parseInt(splitDate[1])==each.getDateOfBillGeneration().getMonth() && Integer.parseInt(splitDate[0])==each.getDateOfBillGeneration().getYear()){
                System.out.println(each.getCreditCardHolder()+" "+each.getCreditCardNumber());
            }
        }
    }

    public void updatePIN(CreditCard[] customers,Long creditCardNumber){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter updated PIN");
         int updatePIN=scanner.nextInt();
         for(CreditCard each:customers){
             if(each.getCreditCardNumber().equals(creditCardNumber)){
                 each.setCreditCardPin(updatePIN);
                 System.out.println("Your PIN is now updated");
             }
         }

    }

}
