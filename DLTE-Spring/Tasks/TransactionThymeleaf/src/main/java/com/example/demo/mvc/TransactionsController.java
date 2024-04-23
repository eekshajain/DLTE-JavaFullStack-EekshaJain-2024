package com.example.demo.mvc;

import com.example.demo.entity.Transaction;
import com.example.demo.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String saveTransaction(Model model){
        model.addAttribute("transaction",new Transaction());
        return "newTransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(Transaction transaction1,Model model){
      transactionServices.apiSave(transaction1);
      return "index";
    }
//    public String newTransaction(@RequestParam("transId") String id,@RequestParam("transDate") String date,@RequestParam("transBy") String by,@RequestParam("transTo") String to,@RequestParam("transRemark") String remark,@RequestParam("transAmount") String amount){
//     Transaction transaction=new Transaction();
//     transaction.setTransactionId(Long.parseLong(id));
//     transaction.setTransactionDate(Date.valueOf(date));
//     transaction.setTransactionBy(by);
//     transaction.setTransactionTo(to);
//     transaction.setTransactionRemarks(remark);
//     transaction.setTransactionAmount(Integer.parseInt(amount));
//     transactionServices.apiSave(transaction);
//     return "index";
//    }
}
