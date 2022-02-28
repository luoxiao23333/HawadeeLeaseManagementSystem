package com.group5.hawadeeleasemanagementsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class WelcomeController {

    @RequestMapping(value = "/")
    public ModelAndView welcome(){
        return new ModelAndView("index");
    }
}
