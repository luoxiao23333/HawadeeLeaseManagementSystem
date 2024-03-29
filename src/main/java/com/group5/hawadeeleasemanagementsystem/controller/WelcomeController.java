package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.service.ContractProcessingHistoryService;
import com.group5.hawadeeleasemanagementsystem.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/")
    public ModelAndView welcome(HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/account/login");
        mv.addObject("isShowLoginFailed", false);
        return mv;
    }

}
