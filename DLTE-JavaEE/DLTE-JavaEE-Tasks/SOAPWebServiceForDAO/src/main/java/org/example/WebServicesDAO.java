package org.example;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebServicesDAO {
  AccountService accountService;


    public WebServicesDAO() {
        accountService=new AccountService(new DatabaseTarget());
    }

    @WebResult(name="findAll")
    public ArrayList<Transaction> findAll(){
        ArrayList<Transaction> allTransactions= (ArrayList<Transaction>) accountService.callFindAll();
        return allTransactions;
    }


    @WebResult(name="createAccount")//method to create account
    public void createNewAccount(long accountNumber,long customerId, String email,String name, double balance, String username, String password){
        accountService.callAddAccounts(new Account(accountNumber,customerId,email,name,balance,username,password));
    }

    @WebResult(name = "findByAccount")//method to find account
    public Account findByUser(String username){
        Account account=accountService.callFindByUsername(username);
        return account;
    }

    @WebResult(name="withdraw")//method to add transaction
    public Double withdraw(String username,String password,double withdrawAmount){
        double balance=accountService.callWithdraw(username,password,withdrawAmount);
        return balance;
    }
}
