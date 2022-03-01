package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.ContractInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.service.ContractProcessingHistoryService;
import com.group5.hawadeeleasemanagementsystem.service.ContractInfoService;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {
    private UserService userService;
    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    private ContractInfoService contractInfoService;
    @Autowired
    private void setUserService(ContractInfoService contractInfoService){
        this.contractInfoService = contractInfoService;
    }

    private ContractProcessingHistoryService contractInfoHistoryService;
    @Autowired
    private void setUserService(ContractProcessingHistoryService contractInfoHistoryService){
        this.contractInfoHistoryService = contractInfoHistoryService;
    }

    @RequestMapping(value = "/")
    public ModelAndView welcome(){
        ModelAndView mv = new ModelAndView("/login");
        mv.addObject("user", new User());
        mv.addObject("isShowLoginFailed", false);
        mv.addObject("newContract", new ContractInfo());
        return mv;
    }

    private void updateContractInfo(ModelAndView mv, User user){
        List<ContractInfo> contractInfoList = contractInfoService.getContractUserPromoted(user);
        Map<ContractInfo, List<ContractProcessingHistory>> contractProcessingHistoryMap =
                contractInfoHistoryService.getContractProcessingHistoryMap(contractInfoList);

        mv.addObject("contractPromoted", contractInfoList);
        mv.addObject("contractNeedToProcess", contractInfoService.getContractUserNeedToProcess(user));
        mv.addObject("contractProcessingHistoryMap", contractProcessingHistoryMap);
    }

    @RequestMapping(value = "doLogin")
    public ModelAndView doLogin(User user, HttpSession session){
        user = userService.login(user);
        ModelAndView mv = new ModelAndView();
        // login fail
        if(user == null){
            mv.setViewName("/login");
            mv.addObject("isShowLoginFailed", true);
            return mv;
        }

        this.updateContractInfo(mv, user);

        session.setAttribute("user",user);
        mv.setViewName("/index");
        return mv;
    }

    @RequestMapping(value = "/newContract")
    public ModelAndView newContract(@RequestParam(name = "title") String contractTitle,
                                    @RequestParam(name = "content") String contractContent,
                                    HttpSession session){
        User user = (User) session.getAttribute("user");
        ContractInfo contract = new ContractInfo();
        contract.setContent(contractContent);
        contract.setTitle(contractTitle);
        contract.setPromoterId(user.getId());
        contractInfoService.addNewContract(contract);

        ModelAndView mv = new ModelAndView("/index");

        this.updateContractInfo(mv, user);

        return mv;
    }

    @RequestMapping(value = "processing")
    public ModelAndView processContract(@RequestParam(name = "isApprove") boolean isApprove,
                                        @RequestParam(name = "contractId") Integer contractId,
                                        @RequestParam(name = "reason") String reason,
                                        HttpSession session){
        User user = (User) session.getAttribute("user");
        contractInfoService.processContract(contractId, user, isApprove, reason);
        ModelAndView mv = new ModelAndView("index");
        this.updateContractInfo(mv, user);
        return mv;
    }

}
