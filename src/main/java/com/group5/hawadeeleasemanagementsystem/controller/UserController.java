package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.entity.UserEntity;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


/*
登录
 */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        UserEntity user = userService.getById(request.getParameter("userid"));
        if (user != null && user.getPassword().equals(request.getParameter("password"))) {
            //modelAndView.addObject("user",user);
            session.setAttribute("user", user);
            modelAndView.setViewName("/index");
            return modelAndView;
        }
        modelAndView.setViewName("/login/login");
        return modelAndView;
    }
}
