package com.payment.webservices.mvccontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

@Controller
@RequestMapping("/transaction")
public class MyBank {
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
}
