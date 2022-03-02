package com.group5.hawadeeleasemanagementsystem.controller;


import com.group5.hawadeeleasemanagementsystem.entity.ContrastEntity;
import com.group5.hawadeeleasemanagementsystem.entity.UserEntity;
import com.group5.hawadeeleasemanagementsystem.service.ContrastService;
import com.group5.hawadeeleasemanagementsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杜小龙
 * @since 2022-03-02
 */
@Controller
@RequestMapping("/contrast")
public class ContrastController {

    @Autowired
    ContrastService contrastService;
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/apply")
    public String applyForContrast (){
        return "/contrast/applyforcontrast";
    }

    @RequestMapping("/add")
    public String addContrast(HttpServletRequest request){
        UserEntity user = (UserEntity) request.getSession().getAttribute("user");
        ContrastEntity contrast = new ContrastEntity();
        contrast.setContrastId((int)contrastService.count()+20220000);
        contrast.setStarterId(user.getUserid());
        contrast.setLawManager(Integer.parseInt(departmentService.getById(1001).getChairmanNumber()));
        contrast.setRiskManager(Integer.parseInt(departmentService.getById(1002).getChairmanNumber()));
        contrast.setFinalManager(Integer.parseInt(departmentService.getById(1000).getChairmanNumber()));
        contrast.setContrastName(request.getParameter("contrastname"));
        contrast.setContrastDescription(request.getParameter("contrastdescription"));
        contrast.setCurrentManager(Integer.parseInt(departmentService.getById(1001).getChairmanNumber()));
        contrastService.save(contrast);
        return "/index";
    }

}

