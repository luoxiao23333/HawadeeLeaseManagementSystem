package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.service.AccountService;
import com.group5.hawadeeleasemanagementsystem.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class AccountController {

    @Autowired
    SMSService smsService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("/account/login");
        return mv;
    }

    @RequestMapping(value = "/account/doLogin")
    public ModelAndView doLogin(@RequestParam String username,
                                @RequestParam String password){

    }

    @RequestMapping(value = "/account/registerCancel")
    public ModelAndView registerCancel(){
        return new ModelAndView("/account/login");
    }

    @RequestMapping(value = "/account/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("/account/register");
        mv.addObject("username", "");
        mv.addObject("password", "");
        mv.addObject("retypePassword", "");
        mv.addObject("phone", "");
        return mv;
    }

    @RequestMapping(value = "/account/doRegister")
    public ModelAndView doRegister(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String retypePassword,
                                   @RequestParam String phone,
                                   @RequestParam String verificationCode,
                                   @RequestParam String button,
                                   HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView();

        if(button.equals("code")){
            String generatingCode = smsService.sendVerificationCode(phone);
            session.setAttribute("code", generatingCode);
            mv.setViewName("/account/register");
            mv.addObject("isShowCodeAdded", true);
            mv.addObject("username", username);
            mv.addObject("password", password);
            mv.addObject("retypePassword", retypePassword);
            mv.addObject("phone", phone);
            return mv;
        }
        else if(button.equals("register")){
            mv.setViewName("/account/register");
            if(!password.equals(retypePassword)){
                mv.addObject("isShowPasswordNotEqual", true);
                return mv;
            }

            String code = (String) session.getAttribute("code");
            if(!code.equals(verificationCode)){
                mv.addObject("isShowCodeWrong");
                return mv;
            }

            accountService.addNewAccount(username, password, phone);

            mv.setViewName("/index");
            return mv;
        }
        else{
            throw new Exception("UnSupported button");
        }

    }
}
