package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.*;
import com.group5.hawadeeleasemanagementsystem.service.ContractInfoService;
import com.group5.hawadeeleasemanagementsystem.service.ContractProcessingHistoryService;
import com.group5.hawadeeleasemanagementsystem.service.FileService;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ContractController {
    private ContractInfoService contractInfoService;

    @Autowired
    private void setUserService(ContractInfoService contractInfoService) {
        this.contractInfoService = contractInfoService;
    }

    private ContractProcessingHistoryService contractInfoHistoryService;

    @Autowired
    private void setUserService(ContractProcessingHistoryService contractInfoHistoryService) {
        this.contractInfoHistoryService = contractInfoHistoryService;
    }

    private FileService fileService;
    @Autowired
    private void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    private void updateContractInfo(ModelAndView mv, User user) {
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
    public ModelAndView contractManagement(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("/contract/contractManagement");
        this.updateContractInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/contract/newContract")
    public ModelAndView newContract(@RequestParam(name = "title") String contractTitle,
                                    @RequestPart(name = "file") MultipartFile file,
                                    HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");

        String fileLoc = fileService.save(file);

        String content = (String) session.getAttribute("content");
        session.removeAttribute("content");
        session.removeAttribute("contractId");
        session.removeAttribute("type");

        String contentPath = fileService.save(content, "", ".html");

        ContractInfo contract = new ContractInfo();
        contract.setContentLoc(contentPath);
        contract.setTitle(contractTitle);
        contract.setPromoterId(user.getId());
        contract.setFileLoc(fileLoc);
        contractInfoService.addNewContract(contract);

        ModelAndView mv = new ModelAndView("/contract/contractManagement");

        this.updateContractInfo(mv, user);

        return mv;
    }

    @RequestMapping(value = "/contract/processing")
    public ModelAndView processContract(@RequestParam(name = "isApprove") boolean isApprove,
                                        @RequestParam(name = "contractId") Integer contractId,
                                        @RequestParam(name = "reason") String reason,
                                        HttpSession session) {
        User user = (User) session.getAttribute("user");
        contractInfoService.processContract(contractId, user, isApprove, reason);
        ModelAndView mv = new ModelAndView("/contract/contractManagement");
        this.updateContractInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/contract/download")
    public ModelAndView download(@RequestParam(name = "fileLoc") String fileLoc,
                                 HttpServletResponse response) throws IOException {
        fileService.loadToServlet(fileLoc, response);
        return new ModelAndView("/contract/contractManagement");
    }

    /**
     * @param type type
     *             type=1 新建合同
     *             type=2 修改合同
     */
    @RequestMapping(value = "/contract/editContent")
    public ModelAndView editContent(@RequestParam(name = "type") Integer type,
                                    @RequestParam(name = "contractId") Integer contractId,
                                    HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/contract/contentEdit");
        session.setAttribute("contractId", contractId);
        session.setAttribute("type", type);

        if(type == 1){
            String content = (String) session.getAttribute("content");
            mv.addObject("defaultContent", content);
        }else if(type == 2){
            String contentFileName = contractInfoService.getContractById(contractId).getContentLoc();
            String content = fileService.read(contentFileName);
            mv.addObject("defaultContent", content);
        }else{
            throw new Exception("Unsupported type");
        }
        return mv;
    }

    @RequestMapping(value = "/contract/doEditContent")
    public ModelAndView doEditContent(@RequestParam(name = "content") String content,
                                      HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/contract/contractManagement");
        Integer contractId = (Integer) session.getAttribute("contractId");
        Integer type = (Integer) session.getAttribute("type");

        if (type == 1) {
            session.setAttribute("content", content);
        } else if (type == 2) {
            this.contractInfoService.updateContentById(contractId, content);
        } else {
            throw new Exception("Unsupported type");
        }
        return mv;
    }

    @RequestMapping(value = "/contract/cancelEditContent")
    public ModelAndView cancelEditContent(){
        return new ModelAndView("redirect:/contract/contractManagement");
    }

    @RequestMapping(value = "/contract/contentView")
    public ModelAndView contentView(@RequestParam(name = "contractId") Integer contractId) throws Exception {
        ModelAndView mv = new ModelAndView("/contract/contentView");
        if(contractId == -1){
            mv.addObject("content", "<p>未编辑过哦</p>");
        }else{
            String fileName = contractInfoService.getContractById(contractId).getContentLoc();
            String content = fileService.read(fileName);

            mv.addObject("content", content);
        }

        return mv;
    }
}
