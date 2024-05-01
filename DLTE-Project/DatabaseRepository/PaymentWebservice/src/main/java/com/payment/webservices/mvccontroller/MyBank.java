package com.payment.webservices.mvccontroller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Controller
@RequestMapping("/payment")
public class MyBank {
    @GetMapping("/")
    public String login(){
        return "index";
    }

    @PostMapping("/")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "index";
    }

    @RequestMapping(value = "/dash",method = RequestMethod.POST)
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

    @GetMapping("/dash")
    public String showDash(){
        return "dashboard";
    }

    @GetMapping("/view")
    public String showViewAll(){return "viewall";}

    @GetMapping("/new-trans")
    public String showTrans(){ return "newtransaction";}
}
