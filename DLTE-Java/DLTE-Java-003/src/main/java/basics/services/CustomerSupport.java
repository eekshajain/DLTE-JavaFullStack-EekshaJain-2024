package basics.services;

import java.util.Date;

public class CustomerSupport {
    public static void main(String[] args) {
        //CreditCard creditCard = new CreditCard();
 //  CreditCard[] myBank=new CreditCard[10];
   CreditCard[] myBank={  // anonymous way of object creation
           new CreditCard(89279792766L,"Eeksha Jain",213,new Date(2024,12,3),10000,2134,new Date(2024,2,13),new Date(2024,3,1))
   };
CustomerSupport support =new CustomerSupport();
support.findExpiryDate(myBank);
//support
    }

    public void findExpiryDate(CreditCard[] customers)
    {
        for(CreditCard each:customers){
            if(each.getCreditCardExpiry().after(new Date(2024,11,3))){
                System.out.println(each.getCreditCardHolder()+" your credit card "+each.getCreditCardNumber()+" needs to be upgraded");
            }

        }
    }
    public void findBileDate(CreditCard[] customers,Date start,Date end){
        System.out.println("Customers having bill date");
        for(CreditCard each:customers){
            if(each.getDateOfBillGeneration().getDate()>=start.getDate() && each.getDateOfBillGeneration().getDate()<=end.getDate()){
                System.out.println(each.getCreditCardHolder()+" "+each.getDateOfBillGeneration());
            }
        }
    }

//    public void sortingCustomers(CreditCard[] customers){
//        CreditCard[] backup=null;
//        for(int select=0;select<customers.length-1;select++){
//            for(int next=select+1;next<customers.length;next++){
//                if(customers[select].getCreditCardHolder().compareTo(customers[next].getCreditCardHolder())){
//                   backup=customers[select];
//
//                }
//            }
//        }
//    }


}
