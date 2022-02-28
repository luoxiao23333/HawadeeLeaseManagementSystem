package com.group5.hawadeeleasemanagementsystem.controller;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class dxlController {
    @RequestMapping("/dxl")
    public ModelAndView dxlTest(){
        System.out.println("fufufufufufu");
        return new ModelAndView("dxl/dxlTest");
    }
}
