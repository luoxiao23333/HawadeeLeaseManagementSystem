package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.service.DataVisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataVisualizationController {
    DataVisualizationService dataVisualizationService;
    @Autowired
    private void setDataVisualizationService(DataVisualizationService dataVisualizationService){
        this.dataVisualizationService = dataVisualizationService;
    }

    @RequestMapping(value = "/dataVisualization/comprehensivePanel")
    public ModelAndView comprehensivePanel() throws Exception {
        ModelAndView mv = new ModelAndView("/dataVisualization/comprehensivePanel");
        mv.addObject("contractHistoryChartData",
                dataVisualizationService.getProcessingHistoryChartData());
        return mv;
    }
}
