package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.service.ContractProcessingHistoryService;
import com.group5.hawadeeleasemanagementsystem.service.DataVisualizationService;
import com.group5.hawadeeleasemanagementsystem.service.SMSService;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 账号管理
 * 用户登陆后user会被添加到session中-> session.setAttribute("user",user);
 * 用户登出后user会被session移除-> session.removeAttribute("user");
 */

@Controller
public class AccountController {

    SMSService smsService;
    @Autowired
    private void setSmsService(SMSService smsService){
        this.smsService = smsService;
    }

    UserService userService;
    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    ContractProcessingHistoryService contractProcessingHistoryService;
    @Autowired
    private void setContractProcessingHistoryService(ContractProcessingHistoryService contractProcessingHistoryService){
        this.contractProcessingHistoryService = contractProcessingHistoryService;
    }

    DataVisualizationService dataVisualizationService;
    @Autowired
    private void setDataVisualizationService(DataVisualizationService dataVisualizationService){
        this.dataVisualizationService = dataVisualizationService;
    }

    @RequestMapping(value = "/account/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("/account/login");
        mv.addObject("isShowLoginFailed", false);
        return mv;
    }

    @RequestMapping(value = "/account/doLogin")
    public ModelAndView doLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session) throws Exception {
        User user = userService.login(username, password);
        ModelAndView mv = new ModelAndView();
        // login fail
        if (user == null) {
            mv.setViewName("/account/login");
            mv.addObject("isShowLoginFailed", true);
            return mv;
        }

        session.setAttribute("user", user);
        session.setAttribute("isShowDataVisualization", this.dataVisualizationService.isLegalUser(user));
        mv.setViewName("redirect:/display/homepage");
        return mv;

    }

    @RequestMapping(value = "/account/cancel")
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
            if(code == null || !code.equals(verificationCode)){
                mv.addObject("isShowCodeWrong", true);
                return mv;
            }

            boolean isDuplicate = userService.addUser(username, password, phone);
            if(isDuplicate){
                mv.addObject("isShowDuplicate", true);
                return mv;
            }

            mv.setViewName("/account/login");
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

    @RequestMapping(value = "/account/doReset")
    public ModelAndView doReset(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String retypePassword,
                                @RequestParam String phone,
                                @RequestParam String verificationCode,
                                @RequestParam String button,
                                HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView();

        if(button.equals("code")){
            String generatingCode = smsService.sendVerificationCode(phone);
            session.setAttribute("code", generatingCode);
            mv.setViewName("/account/resetPassword");
            mv.addObject("isShowCodeAdded", true);
            mv.addObject("username", username);
            mv.addObject("password", password);
            mv.addObject("retypePassword", retypePassword);
            mv.addObject("phone", phone);
            return mv;
        }
        else if(button.equals("reset")){
            mv.setViewName("/account/resetPassword");
            if(!password.equals(retypePassword)){
                mv.addObject("isShowPasswordNotEqual", true);
                return mv;
            }

            String code = (String) session.getAttribute("code");
            if(!code.equals(verificationCode)){
                mv.addObject("isShowCodeWrong", true);
                return mv;
            }

            userService.resetPassword(username, password);
            userService.resetPhone(username, phone);

            mv.setViewName("/index");
            return mv;
        }
        else{
            throw new Exception("UnSupported button");
        }
    }

    @RequestMapping(value = "/account/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("user");
        return new ModelAndView("/account/login");
    }
}
