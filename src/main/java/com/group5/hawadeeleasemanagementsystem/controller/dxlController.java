package com.group5.hawadeeleasemanagementsystem.controller;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dxl")
public class dxlController {
    public String dxlTest(){
        return "dxlTest";
    }
}
