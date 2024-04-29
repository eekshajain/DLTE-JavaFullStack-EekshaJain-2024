package com.example.demo.mvc;

import com.example.demo.entity.Transaction;
import com.example.demo.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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

    @RequestMapping(value="/dash", method = RequestMethod.POST)
    public String homePage(){
        return "dashboard";
    }

    @GetMapping("/new")
    public String show(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "newTransaction";
    }
    @RequestMapping(value = "/new" ,method = RequestMethod.POST)
    public String newTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult bindingResult, Model model){
        model.addAttribute("transaction",transaction);
        if(!bindingResult.hasErrors()){
            Transaction transaction1=transactionServices.apiSave(transaction);
            model.addAttribute("message","Transaction successful!");
            model.addAttribute("transaction",transaction1);
            return "dashboard";
         }else{
            model.addAttribute("message","Transaction failed!");
            return "newTransaction";
        }
    }

    @GetMapping("/search")
    public String searchShow(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "filterBy";
    }

    @GetMapping("/results")
    public String search(@RequestParam("filterBy") String filterBy, @RequestParam("search") String searchTerm,Model model){
        System.out.println("Filter By:"+filterBy);
        System.out.println("Search Term:"+searchTerm);
        List<Transaction> transactionList=null;
        switch (filterBy){
            case "filterBySender":transactionList=transactionServices.apiFindBySender(searchTerm);
                                    break;
            case "filterByReceiver":transactionList=transactionServices.apiFindByReceiver(searchTerm);
                                    break;
            case "filterByAmount":transactionList=transactionServices.apiFindByAmount(Integer.parseInt(searchTerm));
                                    break;
        }
        model.addAttribute("transactions",transactionList);
        return "filterBy";
    }

    @GetMapping("/before")
    public String deleteShow(Model model){
        //Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "delete";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("startDate") String startDateStr,@RequestParam("endDate") String endDateStr,Model model){
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
        Date startDate;
        Date endDate;
        try {
            startDate = (Date) dateFormat.parse(startDateStr);
            endDate = (Date) dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            // Handle parse exception if needed
            e.printStackTrace();
            return "redirect:/transactions/error";
        }
           String delete=transactionServices.deleteTransaction(startDate,endDate);
           model.addAttribute("messageDelete",delete);
           return "index";
    }
    @PostMapping("/logout")
    public String logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/index";
    }
}
