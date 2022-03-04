package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.*;
import com.group5.hawadeeleasemanagementsystem.service.ContractInfoService;
import com.group5.hawadeeleasemanagementsystem.service.ContractProcessingHistoryService;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ContractController {
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

    private void updateContractInfo(ModelAndView mv, User user){
        List<ContractWithUser> contractsPromoted = contractInfoService.getContractUserPromoted(user);
        List<ContractWithUser> contractsNeedToProcess = contractInfoService.getContractUserNeedToProcess(user);
        Map<ContractWithUser, List<ContractHistoryWithUser>> contractPromotedProcessingHistoryMap =
                contractInfoHistoryService.getContractProcessingHistoryMap(contractsPromoted);
        Map<ContractWithUser, List<ContractHistoryWithUser>> contractNeedToProcessHistoryMap =
                contractInfoHistoryService.getContractProcessingHistoryMap(contractsNeedToProcess);

        mv.addObject("contractPromoted", contractsPromoted);
        mv.addObject("contractNeedToProcess", contractInfoService.getContractUserNeedToProcess(user));
        mv.addObject("contractPromotedProcessingHistoryMap", contractPromotedProcessingHistoryMap);
        mv.addObject("contractNeedToProcessHistoryMap", contractNeedToProcessHistoryMap);
    }

    @RequestMapping(value = "/contract/contractManagement")
    public ModelAndView contractManagement(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("/contract/contractManagement");
        this.updateContractInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/contract/newContract")
    public ModelAndView newContract(@RequestParam(name = "title") String contractTitle,
                                    @RequestParam(name = "content") String contractContent,
                                    HttpSession session){
        User user = (User) session.getAttribute("user");
        ContractInfo contract = new ContractInfo();
        contract.setContent(contractContent);
        contract.setTitle(contractTitle);
        contract.setPromoterId(user.getId());
        contractInfoService.addNewContract(contract);

        ModelAndView mv = new ModelAndView("/contract/contractManagement");

        this.updateContractInfo(mv, user);

        return mv;
    }

    @RequestMapping(value = "/contract/processing")
    public ModelAndView processContract(@RequestParam(name = "isApprove") boolean isApprove,
                                        @RequestParam(name = "contractId") Integer contractId,
                                        @RequestParam(name = "reason") String reason,
                                        HttpSession session){
        User user = (User) session.getAttribute("user");
        contractInfoService.processContract(contractId, user, isApprove, reason);
        ModelAndView mv = new ModelAndView("/contract/contractManagement");
        this.updateContractInfo(mv, user);
        return mv;
    }
}
