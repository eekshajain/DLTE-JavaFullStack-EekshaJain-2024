package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalysis {
    public static void main(String[] args) {
        System.out.println("--------Welcome to MyBank--------");
        Scanner scanner=new Scanner(System.in);
        Integer startDate,endDate;
        String beneficiaryName="",remarks="";
        int option=0;

        List<Transaction> transactionList=new ArrayList<>();
        transactionList.add(new Transaction(new Date(2024,2,14),2000,"Divija","Friend"));
        transactionList.add(new Transaction(new Date(2024,2,15),1250,"BMTC","Bills"));
        transactionList.add(new Transaction(new Date(2024,1,20),1250,"Spandana","Family"));
        transactionList.add(new Transaction(new Date(2024,2,1),300,"Spandana","Family"));
        transactionList.add(new Transaction(new Date(2024,2,13),650,"Sudha Clinic","Emergency"));
        transactionList.add(new Transaction(new Date(2024,1,17),20000,"MITE","Education"));
        transactionList.add(new Transaction(new Date(2024,1,10),200,"Ankitha","Friend"));
        TransactionAnalysis analysis=new TransactionAnalysis();
        System.out.println("Enter\n1.Filter Based On transaction Date\n2.Least amount transferred\n3.Maximum amount transferred\n4.Sort based on property");
        System.out.println("Enter your choice");
        option=scanner.nextInt();
        switch (option){
            case 1:
                System.out.println("Enter Start date(dd)");
                startDate=scanner.nextInt();
                System.out.println("Enter End Date");
                endDate=scanner.nextInt();
                analysis.filterBasedOnDate(transactionList,startDate,endDate);
                break;
            case 2:analysis.leastAmountTransferred(transactionList);
                break;
            case 3:analysis.maximumAmountTransferred(transactionList);
                break;
            case 4:
                System.out.println("Enter sort property(date,amount,sentTo,remarks)");
                String property=scanner.next();
                System.out.println("Enter order of sort(asc,desc)");
                String sortOrder=scanner.next();
                Comparator<Transaction> comparator=null;
                switch (property.toLowerCase()){
                    case "date":comparator=Comparator.comparing(Transaction::getDateOfTransaction);
                                break;
                    case "amount":comparator=Comparator.comparing(Transaction::getAmountOfTransaction);
                                  break;
                    case "sentTo":comparator=Comparator.comparing(Transaction::getSentTo);
                                  break;
                    case "remarks":comparator=Comparator.comparing(Transaction::getRemarks);
                                   break;
                    default:
                        System.out.println("Invalid property");
                        break;
                }
                if(sortOrder.equalsIgnoreCase("desc")) comparator=comparator.reversed();
                analysis.sortTransaction(transactionList,comparator);
        }
        scanner.close();
    }

    public void filterBasedOnDate(List<Transaction> myData, Integer startDate,  Integer endDate){
        System.out.println("Details whose date of transaction are between "+startDate+" and "+endDate);
       List<Transaction> transactions=myData.stream().filter(transaction->transaction.getDateOfTransaction().getDate()>=startDate && transaction.getDateOfTransaction().getDate()<=endDate ).collect(Collectors.toList());
       transactions.forEach(transaction -> System.out.println(transaction.getSentTo()+" "+transaction.getAmountOfTransaction()));
    }

    public void leastAmountTransferred(List<Transaction> myData){
        Transaction transactions= myData.stream().min(Comparator.comparing(Transaction::getAmountOfTransaction)).orElse(null);
         System.out.println("Minimum amount was sent to "+transactions.getSentTo());
    }


    public void maximumAmountTransferred(List<Transaction> myData){
        Transaction transactions= myData.stream().max(Comparator.comparing(Transaction::getAmountOfTransaction)).orElse(null);
        System.out.println("Minimum amount was sent to "+transactions.getSentTo());
    }
   public void sortTransaction(List<Transaction> myData,Comparator<Transaction> comparator){
     List<Transaction> transactions=myData.stream().sorted(comparator).collect(Collectors.toList());
       System.out.println(transactions);;
   }
}
