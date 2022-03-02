package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.service.SMSService;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    @Autowired
    SMSService smsService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/account/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("/account/login");
        mv.addObject("isShowLoginFailed", false);
        return mv;
    }

    @RequestMapping(value = "/account/doLogin")
    public ModelAndView doLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) {
        User user = userService.login(username, password);
        ModelAndView mv = new ModelAndView();
        // login fail
        if (user == null) {
            mv.setViewName("/login");
            mv.addObject("isShowLoginFailed", true);
            return mv;
        }

        session.setAttribute("user", user);
        mv.setViewName("/index");
        return mv;

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
        mv.addObject("isShowDuplicate", false);
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

            boolean isDuplicate = userService.addUser(username, password, phone);
            if(isDuplicate){
                mv.addObject("isShowDuplicate", true);
                return mv;
            }

            mv.setViewName("/index");
            return mv;
        }
        else{
            throw new Exception("UnSupported button");
        }
    }

    @RequestMapping(value = "/account/reset")
    public ModelAndView reset(){
        return new ModelAndView("/account/resetPassword");
    }
}
