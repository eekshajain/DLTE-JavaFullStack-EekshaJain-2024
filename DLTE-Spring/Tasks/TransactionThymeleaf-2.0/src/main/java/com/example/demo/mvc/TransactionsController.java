package com.example.demo.mvc;

import com.example.demo.entity.Transaction;
import com.example.demo.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionServices transactionServices;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("transaction",new Transaction());
        return "index";
    }

    @GetMapping("/new")
    public String show(Model model){
        model.addAttribute("transaction",new Transaction());
        return "newTransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@ModelAttribute Transaction transaction, Model model){
        model.addAttribute("transaction",transaction);
      Transaction transaction1=transactionServices.apiSave(transaction);
      if(transaction1!=null){
          model.addAttribute("message","Transaction successful!");
          model.addAttribute("transaction",transaction1);
          return "index";
      }else{
          model.addAttribute("message","Transaction failed!");
          return "newTransaction";
      }
    }

}
