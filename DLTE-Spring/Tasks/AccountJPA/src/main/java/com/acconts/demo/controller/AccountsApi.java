package com.acconts.demo.controller;

import com.acconts.demo.model.Accounts;
import com.acconts.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsApi {
    @Autowired
    AccountService accountService;

    /*
    To add accounts use post mapping,account number not specified as its auto increment
    http://localhost:8085/accounts/
    In postman
    {
 "customerId":23459,
   "email":"arundhathi@gmail.com",
   "name":"Arundhathi",
   "balance":53000,
  "username":"arundhathi08",
 "password":"@rundh@thi"
}
     */
  @PostMapping("/")
    public Accounts callAccountOpening(@RequestBody Accounts accounts){
        return accountService.AccountOpening(accounts);
    }
/*
To update in body pass json object along with account number
{
   "accountNumber":123456792,
    "customerId":23459,
   "email":"arundhathi@gmail.com",
   "name":"Arundhathi Nayak",
   "balance":53000,
  "username":"arundhathi08",
 "password":"@rundh@thi"
}
account number need to be provided or else new account added
 */
   @PutMapping("/")
    public  Accounts callAccountUpdate(@RequestBody Accounts accounts){
      return accountService.AccountOpening(accounts);
   }
/*
It'll list accounts
[
    {
        "accountNumber": 123456789,
        "customerId": 23456,
        "email": "eekshajain@gmail.com",
        "name": "Eeksha",
        "balance": 50000.0,
        "username": "eeksha06",
        "password": "eekshaj@in"
    },
    {
        "accountNumber": 123456790,
        "customerId": 23457,
        "email": "divija@gmail.com",
        "name": "Divija",
        "balance": 53000.0,
        "username": "divija29",
        "password": "divij@"
    },
    {
        "accountNumber": 123456791,
        "customerId": 23458,
        "email": "ankitha@gmail.com",
        "name": "Ankitha",
        "balance": 52000.0,
        "username": "ankitha30",
        "password": "@nkith@"
    },
    {
        "accountNumber": 123456792,
        "customerId": 23459,
        "email": "arundhathi@gmail.com",
        "name": "Arundhathi Nayak",
        "balance": 53000.0,
        "username": "arundhathi08",
        "password": "@rundh@thi"
    },
    {
        "accountNumber": 123456793,
        "customerId": 23460,
        "email": "annapoorna@gmail.com",
        "name": "Annapoorna",
        "balance": 57000.0,
        "username": "annapoorna07",
        "password": "@nn@poorn@"
    },
    {
        "accountNumber": 123456794,
        "customerId": 23461,
        "email": "akshira@gmail.com",
        "name": "Akshira",
        "balance": 56500.0,
        "username": "akshira12",
        "password": "@kshir@"
    }
]
 */
   @GetMapping("/")
    public List<Accounts> callListAll(){
       return  accountService.AllAccounts();
   }
}
