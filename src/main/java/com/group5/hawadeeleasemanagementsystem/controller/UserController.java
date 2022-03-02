package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.entity.UserEntity;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String welcomePage(){
        return "/login/login";
    }


    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, ModelAndView modelAndView) {
        UserEntity user = userService.getById(request.getParameter("userid"));
        System.out.println(user.getEmail());
        System.out.println("11111111111111111111111111111111111111111111111111");
        System.out.println(user.getPassword());
        System.out.println(request.getParameter("password"));
        if (user != null && user.getPassword().equals(request.getParameter("password"))) {
            modelAndView.addObject("user",user);
            modelAndView.setViewName("/index");
            return modelAndView;
        }
        modelAndView.setViewName("/login/login");
        return modelAndView;
    }
}
