package basics.services;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis {
    public static void main(String[] args) {
        System.out.println("--------Welcome to MyBank--------");
        Scanner scanner=new Scanner(System.in);
        Integer startDate,endDate;
        String beneficiaryName="";
        int option=0;
        TransactionData[] myBank={
                new TransactionData(new Date(2024,2,14),2000,"Divija","Friend"),
                new TransactionData(new Date(2024,2,15),1250,"BMTC","Bills"),
                new TransactionData(new Date(2024,1,20),1250,"Spandana","Family"),
                new TransactionData(new Date(2024,2,1),300,"Spandana","Family"),
                new TransactionData(new Date(2024,2,13),650,"Sudha Clinic","Emergency"),
                new TransactionData(new Date(2024,1,17),20000,"MITE","Education"),
                new TransactionData(new Date(2024,1,10),200,"Ankitha","Friend"),
        };
        TransactionAnalysis analysis=new TransactionAnalysis();
        System.out.println("Enter\n1.Filter Based On transaction Date\n2.Least amount transferred\n3.Maximum amount transferred\n4.Number of transaction to particular beneficiary\n5.Filter based on particular remarks");
        option=scanner.nextInt();
       switch (option){
           case 1:
               System.out.println("Enter Start date(dd)");
               startDate=scanner.nextInt();
               System.out.println("Enter End Date");
               endDate=scanner.nextInt();
               analysis.filterBasedOnDate(myBank,startDate,endDate);
               break;
           case 2:analysis.leastAmountTransferred(myBank);
                 break;
           case 3:analysis.maximumAmountTransferred(myBank);
                  break;
           case 4:
               System.out.println("Enter beneficiary name");
               beneficiaryName=scanner.next();
               analysis.numberOfTransaction(myBank,beneficiaryName);
               break;
       }
    }
    public void filterBasedOnDate(TransactionData[] myData,Integer startDate,Integer endDate){
        System.out.println("Details whose date of transaction are between "+startDate+" and "+endDate);
        for(TransactionData each:myData){
            if(each.getDateOfTransaction().getDate()>=startDate && each.getDateOfTransaction().getDate()<endDate){
                System.out.println(each.getSentTo()+" "+each.getAmountOfTransaction());
            }
        }
    }

    public void leastAmountTransferred(TransactionData[] myData){
        Integer min =Integer.MAX_VALUE;
        for(TransactionData each:myData){
           if(each.getAmountOfTransaction()<min){
               min=each.getAmountOfTransaction();
           }
        }
        for(TransactionData each:myData){
            if(each.getAmountOfTransaction().equals(min)){
                System.out.println("Minimum Amount of "+min+" was sent to "+each.getSentTo());
            }
        }
    }

    public void maximumAmountTransferred(TransactionData[] myData){
        Integer max =Integer.MIN_VALUE;
        for(TransactionData each:myData){
            if(each.getAmountOfTransaction()>max){
                max=each.getAmountOfTransaction();
            }
        }
        for(TransactionData each:myData){
            if(each.getAmountOfTransaction().equals(max)){
                System.out.println("Maximum Amount of "+max+" was sent to "+each.getSentTo());
            }
        }
    }

    public void numberOfTransaction(TransactionData[] myData,String beneficiary){
        int count=0;
        for (TransactionData each:myData){
            if(each.getSentTo().equals(beneficiary)){
                count++;
            }
        }
        System.out.println("Number of transaction made to beneficiary "+beneficiary+" is "+count);
    }

    public void

}
