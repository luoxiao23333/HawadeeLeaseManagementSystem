package com.group5.hawadeeleasemanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class AccountController {

    @RequestMapping(value = "/account/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("/account/login");
        return mv;
    }

    @RequestMapping(value = "/account/register")
    public ModelAndView register(){
        return new ModelAndView("/account/register");
    }

    @RequestMapping(value = "/account/doRegister")
    public ModelAndView doRegister(@RequestParam(name = "button") String button){
        ModelAndView mv = new ModelAndView();
        if(button.equals("cancel")){
            mv.setViewName("/account/login");
            return mv;
        }
        else if(button.equals("register")){
            mv.setViewName("/account/login");
            return mv;
        }
        return mv;
    }
}
