package org.webservices;

import org.example.Account;
import org.example.WebServicesDAO;
import org.example.WebServicesDAOService;

public class WebServiceClient {
    public static void main(String[] args) {
        WebServicesDAOService webServicesDAOService=new WebServicesDAOService();
        WebServicesDAO webServicesDAO=webServicesDAOService.getWebServicesDAOPort();
        webServicesDAO.createNewAccount(12345678L,45678L,"jayathi@gmail.com","Jayathi",52000,"jayathi16","jayathi1234");//to add new account
        Account account=webServicesDAO.findByUser("eeksha25");//search bu username
        System.out.println("Name:"+account.getName()+"\nAccount Number:"+account.getAccountNumber()+"\nCustomer ID:"+account.getCustomerId()+"\nAccount Balance:"+account.getBalance());
        double balance=webServicesDAO.withdraw("jayathi16","jayathi1234",1000);
        System.out.println("Your transaction of Rs 1000 is deducted.Your current balance is "+balance);//add transaction after deduction
    }
}
