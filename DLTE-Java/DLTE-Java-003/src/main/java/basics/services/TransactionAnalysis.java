package basics.services;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis {
    public static void main(String[] args) {
        System.out.println("--------Welcome to MyBank--------");
        Scanner scanner=new Scanner(System.in);
        Integer startDate,endDate;
        String beneficiaryName="",remarks="";
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
        System.out.println("Enter\n1.Filter Based On transaction Date\n2.Least amount transferred\n3.Maximum amount transferred\n4.Number of transaction to particular beneficiary\n5.Filter based on particular remarks\n6.Sort beneficiary in descending\n7.Sort amount in ascending");
        System.out.println("Enter your choice");
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
           case 5:
               System.out.println("Enter the remarks based on which you want to filter");
               remarks=scanner.next();
               analysis.filterBasedRemarks(myBank,remarks);
               break;
           case 6:
               System.out.println("Sort beneficiary in descending");
               analysis.sortBasedOnBeneficiary(myBank);
               analysis.list(myBank);
               break;
           case 7:
               System.out.println("Sort amount in ascending");
               analysis.sortBasedOnAmount(myBank);
               analysis.list(myBank);
       }
       scanner.close();
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

    public void filterBasedRemarks(TransactionData[] myData,String remarks){
        System.out.println("Details of those whose remarks is "+remarks);
        for(TransactionData each:myData){
            if(each.getRemarks().equals(remarks)){
                System.out.println(each.getSentTo()+"  "+each.getAmountOfTransaction()+" "+each.getDateOfTransaction());
            }
        }
    }
    public void list(TransactionData[] myData){
        System.out.println("Available customers");
        for(TransactionData each:myData){
            System.out.println(each);
        }
    }

    public void sortBasedOnBeneficiary(TransactionData[] myData){
      TransactionData temp=null;
      for(int index=0;index<myData.length-1;index++){
          for (int select=0;select<myData.length-index-1;select++){
              if(myData[select].getSentTo().compareTo(myData[select+1].getSentTo())<0){
                  temp=myData[select];
                  myData[select]=myData[select+1];
                  myData[select+1]=temp;
              }
          }
      }
    }

    public void sortBasedOnAmount(TransactionData[] myData){
        TransactionData temp=null;
        for(int index=0;index<myData.length-1;index++){
            for (int select=0;select<myData.length-index-1;select++){
                if(myData[select].getAmountOfTransaction().compareTo(myData[select+1].getAmountOfTransaction())>0){
                    temp=myData[select];
                    myData[select]=myData[select+1];
                    myData[select+1]=temp;
                }
            }
        }
    }

}
