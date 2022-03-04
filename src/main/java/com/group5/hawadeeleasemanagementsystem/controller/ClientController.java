package com.group5.hawadeeleasemanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/client")
public class ClientController {
    @RequestMapping("/clientPage")
    private String clientPage(){
        return "/client/clientPage";
    }

    @RequestMapping("emailPage")
    private String emailPage(){
        return "/client/emailPage";
    }

    @RequestMapping("/list")
    private ModelAndView listClients(HttpServletRequest request){
        return new ModelAndView("/index");
    }

    @RequestMapping("/add")
    private String addClient(HttpServletRequest request){
        return "/index";
    }
}
