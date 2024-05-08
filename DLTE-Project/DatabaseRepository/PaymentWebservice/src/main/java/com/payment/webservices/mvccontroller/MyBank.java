package com.payment.webservices.mvccontroller;


import com.paymentdao.payment.security.MyBankUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Controller
@RequestMapping("/payment")
public class MyBank {
    @Autowired
    MyBankUsersServices service;
    @GetMapping("/")
    public String login(){
        return "index";
    }

    @PostMapping("/")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "index";
    }

    @RequestMapping(value = "/dashboard",method = RequestMethod.POST)
    public String showDashboard(){
        return "dashboard";
    }

    @GetMapping("/head")
    public String showHeader(){
        return "header";
    }

    @GetMapping("/foot")
    public String showFooter(){
        return "footer";
    }

    @GetMapping("/dashboard")
    public String showDash(){
        return "dashboard";
    }

    @GetMapping("/view")
    public String showViewAll(){return "viewall";}

    @GetMapping("/new-trans")
    public String showTrans(){ return "newtransaction";}

    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }

    @GetMapping("/name")
    @ResponseBody
    public String getCustomerName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        String user = service.getCustomerName(name);
        return user;
    }

}
