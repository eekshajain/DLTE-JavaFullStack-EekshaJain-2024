package multi.threading;

import java.util.Scanner;

public class TransactionAnalysis implements Runnable,TransactionAnalysisFunctionality{
Scanner scanner=new Scanner(System.in);
Transaction[] myTransaction;
public TransactionAnalysis(){
    myTransaction=new Transaction[10];
}
TransactionAnalysis myTrans=new TransactionAnalysis();
    public void filterBasedOnDate(Transaction[] myData,Integer startDate,Integer endDate){
        System.out.println("Details whose date of transaction are between "+startDate+" and "+endDate);
        for(Transaction each:myData){
            if(each.getDateOfTransaction().getDate()>=startDate && each.getDateOfTransaction().getDate()<endDate){
                System.out.println(each.getSentTo()+" "+each.getAmountOfTransaction());
            }
        }
    }

    public void leastAmountTransferred(Transaction[] myData){
        Integer min =Integer.MAX_VALUE;
        for(Transaction each:myData){
            if(each.getAmountOfTransaction()<min){
                min=each.getAmountOfTransaction();
            }
        }
        for(Transaction each:myData){
            if(each.getAmountOfTransaction().equals(min)){
                System.out.println("Minimum Amount of "+min+" was sent to "+each.getSentTo());
            }
        }
    }


    public void maximumAmountTransferred(Transaction[] myData){
        Integer max =Integer.MIN_VALUE;
        for(Transaction each:myData){
            if(each.getAmountOfTransaction()>max){
                max=each.getAmountOfTransaction();
            }
        }
        for(Transaction each:myData){
            if(each.getAmountOfTransaction().equals(max)){
                System.out.println("Maximum Amount of "+max+" was sent to "+each.getSentTo());
            }
        }
    }

    public void numberOfTransaction(Transaction[] myData,String beneficiary){
        int count=0;
        for (Transaction each:myData){
            if(each.getSentTo().equals(beneficiary)){
                count++;
            }
        }
        System.out.println("Number of transaction made to beneficiary "+beneficiary+" is "+count);
    }

    public void filterBasedRemarks(Transaction[] myData,String remarks){
        System.out.println("Details of those whose remarks is "+remarks);
        for(Transaction each:myData){
            if(each.getRemarks().equals(remarks)){
                System.out.println(each.getSentTo()+"  "+each.getAmountOfTransaction()+" "+each.getDateOfTransaction());
            }
        }
    }
    public void list(Transaction[] myData){
        System.out.println("Available customers");
        for(Transaction each:myData){
            System.out.println(each);
        }
    }

    public void sortBasedOnBeneficiary(Transaction[] myData){
        Transaction temp=null;
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

    public void sortBasedOnAmount(Transaction[] myData){
        Transaction temp=null;
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

    @Override
    public void run() {

    }
}
