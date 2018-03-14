package com.sun.szk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/loginController")
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "index";
    }
}
