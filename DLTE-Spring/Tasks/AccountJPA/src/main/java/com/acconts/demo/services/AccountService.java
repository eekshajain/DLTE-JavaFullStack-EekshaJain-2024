package com.acconts.demo.services;

import com.acconts.demo.model.Accounts;
import com.acconts.demo.remotes.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountsRepository accountsRepository;


    public Accounts AccountOpening(Accounts accounts){
        return accountsRepository.save(accounts);
    }

    public List<Accounts> AllAccounts(){
        return (List<Accounts>) accountsRepository.findAll();
    }
}
