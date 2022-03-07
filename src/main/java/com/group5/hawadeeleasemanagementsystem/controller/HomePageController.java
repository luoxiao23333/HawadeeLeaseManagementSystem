package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.domain.userInfo;
import com.group5.hawadeeleasemanagementsystem.domain.userRel;
import com.group5.hawadeeleasemanagementsystem.service.userInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {
    userInfoService userinfoService;

    @Autowired
    void setUserinfoService(userInfoService userinfoService){this.userinfoService = userinfoService;}

    @RequestMapping(value = "/display/homepage")
    public ModelAndView displayHomepage(HttpSession session){
        ModelAndView mv = new ModelAndView("homepage/myHomepage");
        User user = (User) session.getAttribute("user");
        mv.addObject("user",user);
        userInfo userinfo = userinfoService.getUserInfo(user.getId());
        mv.addObject("userInfo",userinfo);
        return mv;
    }

    @RequestMapping(value = "/homepage/setInfo")
    public ModelAndView setInfo(@RequestParam(name = "age") Integer age,
                                   @RequestParam(name = "level") Integer level,
                                   @RequestParam(name = "school") String school,
                                   @RequestParam(name = "introduction") String introduction,
                                   HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");

        userinfoService.changeInfo(user.getId(),age,school,level,introduction);
        userInfo userinfo = userinfoService.getUserInfo(user.getId());
        ModelAndView mv = new ModelAndView("homepage/myHomepage");

        mv.addObject("userInfo",userinfo);
        mv.addObject("user",user);
        return mv;
    }
}
