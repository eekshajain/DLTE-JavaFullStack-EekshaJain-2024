package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }
    @GetMapping("/list")
    public String listAll() {
        return "listall";
    }

    @GetMapping("/new")
    public String newTransaction() {
        return "newtransaction";
    }


}
