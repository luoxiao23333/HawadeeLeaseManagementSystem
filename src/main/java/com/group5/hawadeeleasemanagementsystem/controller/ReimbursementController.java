package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.*;
import com.group5.hawadeeleasemanagementsystem.service.ReimbursementInfoService;
import com.group5.hawadeeleasemanagementsystem.service.ReimbursementProcessingHistoryService;
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
import java.util.List;
import java.util.Map;

@Controller
public class ReimbursementController {
    private ReimbursementInfoService reimbursementInfoService;

    @Autowired
    private void setUserService(ReimbursementInfoService reimbursementInfoService) {
        this.reimbursementInfoService = reimbursementInfoService;
    }

    private ReimbursementProcessingHistoryService reimbursementInfoHistoryService;

    @Autowired
    private void setUserService(ReimbursementProcessingHistoryService reimbursementInfoHistoryService) {
        this.reimbursementInfoHistoryService = reimbursementInfoHistoryService;
    }

    private FileService fileService;

    @Autowired
    private void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    private void updateReimbursementInfo(ModelAndView mv, User user) {
        List<ReimbursementWithUser> reimbursementsPromoted = reimbursementInfoService.getReimbursementUserPromoted(user);
        List<ReimbursementWithUser> reimbursementsNeedToProcess = reimbursementInfoService.getReimbursementUserNeedToProcess(user);
        Map<ReimbursementWithUser, List<ReimbursementHistoryWithUser>> reimbursementPromotedProcessingHistoryMap =
                reimbursementInfoHistoryService.getReimbursementProcessingHistoryMap(reimbursementsPromoted);
        Map<ReimbursementWithUser, List<ReimbursementHistoryWithUser>> reimbursementNeedToProcessHistoryMap =
                reimbursementInfoHistoryService.getReimbursementProcessingHistoryMap(reimbursementsNeedToProcess);

        mv.addObject("reimbursementPromoted", reimbursementsPromoted);
        mv.addObject("reimbursementNeedToProcess", reimbursementInfoService.getReimbursementUserNeedToProcess(user));
        mv.addObject("reimbursementPromotedProcessingHistoryMap", reimbursementPromotedProcessingHistoryMap);
        mv.addObject("reimbursementNeedToProcessHistoryMap", reimbursementNeedToProcessHistoryMap);
    }

    @RequestMapping(value = "/reimbursement/reimbursementManagement")
    public ModelAndView reimbursementManagement(HttpSession session) {
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("/reimbursement/reimbursementManagement");
        this.updateReimbursementInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/reimbursement/newReimbursement")
    public ModelAndView newReimbursement(@RequestParam(name = "title") String reimbursementTitle,
                                         @RequestParam(name = "content") String reimbursementContent,
                                         @RequestPart(name = "file") MultipartFile file,
                                         HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");

        String fileLoc = fileService.save(file);

        ReimbursementInfo reimbursement = new ReimbursementInfo();
        reimbursement.setContent(reimbursementContent);
        reimbursement.setTitle(reimbursementTitle);
        reimbursement.setPromoterId(user.getId());
        reimbursement.setProvFileLoc(fileLoc);
        reimbursementInfoService.addNewReimbursement(reimbursement);

        ModelAndView mv = new ModelAndView("/reimbursement/reimbursementManagement");

        this.updateReimbursementInfo(mv, user);

        return mv;
    }

    @RequestMapping(value = "/reimbursement/processing")
    public ModelAndView processReimbursement(@RequestParam(name = "isApprove") boolean isApprove,
                                             @RequestParam(name = "reimbursementId") Integer reimbursementId,
                                             @RequestParam(name = "reason") String reason,
                                             HttpSession session) {
        User user = (User) session.getAttribute("user");
        reimbursementInfoService.processReimbursement(reimbursementId, user, isApprove, reason);
        ModelAndView mv = new ModelAndView("/reimbursement/reimbursementManagement");
        this.updateReimbursementInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/reimbursement/download")
    public ModelAndView download(@RequestParam(name = "fileLoc") String fileLoc,
                                 HttpServletResponse response) throws IOException {
        fileService.loadToServlet(fileLoc, response);
        return new ModelAndView("/reimbursement/reimbursementManagement");
    }
}
