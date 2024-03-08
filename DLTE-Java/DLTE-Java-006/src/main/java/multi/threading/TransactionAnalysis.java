package multi.threading;

import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis implements Runnable{
    Transaction[] myTransaction={
            new Transaction(new Date("2/14/2024"),2000,"Divija","Friend"),
            new Transaction(new Date("2/15/2024"),1250,"BMTC","Bills"),
            new Transaction(new Date("1/20/2024"),1250,"Spandana","Family"),
            new Transaction(new Date("2/1/2024"),300,"Spandana","Family"),
            new Transaction(new Date("2/13/2024"),650,"Sudha Clinic","Emergency"),
            new Transaction(new Date("1/17/2024"),20000,"MITE","Education"),
            new Transaction(new Date("1/10/2024"),200,"Ankitha","Friend"),
    };

  @Override
  public void run() {
      System.out.println("--------Welcome to My Bank--------");
      System.out.println("Dear "+Thread.currentThread().getName()+", What do you want?");
      Scanner scanner=new Scanner(System.in);
      Date startDate,endDate;
      String beneficiaryName="",remarks="";
      int option=0;
      TransactionAnalysis analysis=new TransactionAnalysis();
      System.out.println("Enter\n1.Filter Based On transaction Date\n2.Least amount transferred\n3.Maximum amount transferred\n4.Number of transaction to particular beneficiary\n5.Filter based on particular remarks\n6.Sort beneficiary in descending\n7.Sort amount in ascending");
      System.out.println("Enter your choice");
      option=scanner.nextInt();
      switch (option){
          case 1:
              System.out.println("Enter Start date(mm/dd/yyyy)");
              startDate=new Date(scanner.next());
              System.out.println("Enter End Date(mm/dd/yyyy)");
              endDate=new Date(scanner.next());
              analysis.filterBasedOnDate(myTransaction,startDate,endDate);
              break;
          case 2:analysis.leastAmountTransferred(myTransaction);
              break;
          case 3:analysis.maximumAmountTransferred(myTransaction);
              break;
          case 4:
              System.out.println("Enter beneficiary name");
              beneficiaryName=scanner.next();
              analysis.numberOfTransaction(myTransaction,beneficiaryName);
              break;
          case 5:
              System.out.println("Enter the remarks based on which you want to filter");
              remarks=scanner.next();
              analysis.filterBasedRemarks(myTransaction,remarks);
              break;
          case 6:
              System.out.println("Sort beneficiary in descending");
              analysis.sortBasedOnBeneficiary(myTransaction);
              analysis.list(myTransaction);
              break;
          case 7:
              System.out.println("Sort amount in ascending");
              analysis.sortBasedOnAmount(myTransaction);
              analysis.list(myTransaction);
      }
      scanner.close();
  }


    public void filterBasedOnDate(Transaction[] myData,Date startDate,Date endDate){
        System.out.println("Details whose date of transaction are between "+startDate+" and "+endDate);
        for(Transaction each:myData){
            if(each.getDateOfTransaction().before(endDate) && each.getDateOfTransaction().after(startDate)){
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

    public void displayRemarks(){
        System.out.println("Displaying All Remarks");
        for(Transaction transaction:myTransaction){
            System.out.println(transaction.getRemarks());
        }
    }

    public void displaySentTo(){
        System.out.println("Display list of Transaction made");
        System.out.println("Name\t\tAmount");
        for(Transaction transaction:myTransaction){
            System.out.println(transaction.getSentTo()+"\t\t"+transaction.getAmountOfTransaction());
        }
    }

    public void totalAmountOfTransaction(){
        System.out.println("Total amount of Transaction made");
        int totalAmount=0;
        for(Transaction transaction:myTransaction){
            totalAmount+=transaction.getAmountOfTransaction();
        }
        System.out.println(totalAmount);
    }

}
