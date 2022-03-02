package com.group5.hawadeeleasemanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class welcomeController {
    @RequestMapping("/")
    public String welcomePage(){
        return "/login/login";
    }
}
