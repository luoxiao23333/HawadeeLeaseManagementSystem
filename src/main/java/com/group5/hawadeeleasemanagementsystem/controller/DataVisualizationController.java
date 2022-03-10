package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.dao.DutyDao;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.service.DataVisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class DataVisualizationController {
    DataVisualizationService dataVisualizationService;
    @Autowired
    private void setDataVisualizationService(DataVisualizationService dataVisualizationService){
        this.dataVisualizationService = dataVisualizationService;
    }


    @RequestMapping(value = "/dataVisualization/comprehensivePanel")
    public ModelAndView comprehensivePanel(HttpSession session) throws Exception {

        User user = (User) session.getAttribute("user");
        if(!this.dataVisualizationService.isLegalUser(user)){
            return new ModelAndView("/404");
        }

        ModelAndView mv = new ModelAndView("/dataVisualization/comprehensivePanel");
        mv.addObject("contractHistoryChartData",
                dataVisualizationService.getProcessingHistoryChartData());
        mv.addObject("reimbursementChartData",
                dataVisualizationService.getReimbursementChartData());
        return mv;
    }
}
