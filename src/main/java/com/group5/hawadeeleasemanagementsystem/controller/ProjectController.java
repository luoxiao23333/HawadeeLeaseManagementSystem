package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.*;
import com.group5.hawadeeleasemanagementsystem.service.ProjectInfoService;
import com.group5.hawadeeleasemanagementsystem.service.ProjectProcessingHistoryService;
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
public class ProjectController {
    private ProjectInfoService projectInfoService;
    @Autowired
    private void setUserService(ProjectInfoService projectInfoService){
        this.projectInfoService = projectInfoService;
    }

    private ProjectProcessingHistoryService projectInfoHistoryService;
    @Autowired
    private void setUserService(ProjectProcessingHistoryService projectInfoHistoryService){
        this.projectInfoHistoryService = projectInfoHistoryService;
    }

    private FileService fileService;
    @Autowired
    private void setFileService(FileService fileService){
        this.fileService = fileService;
    }

    private void updateProjectInfo(ModelAndView mv, User user){
        List<ProjectWithUser> projectsPromoted = projectInfoService.getProjectUserPromoted(user);
        List<ProjectWithUser> projectsNeedToProcess = projectInfoService.getProjectUserNeedToProcess(user);
        Map<ProjectWithUser, List<ProjectHistoryWithUser>> projectPromotedProcessingHistoryMap =
                projectInfoHistoryService.getProjectProcessingHistoryMap(projectsPromoted);
        Map<ProjectWithUser, List<ProjectHistoryWithUser>> projectNeedToProcessHistoryMap =
                projectInfoHistoryService.getProjectProcessingHistoryMap(projectsNeedToProcess);

        mv.addObject("projectPromoted", projectsPromoted);
        mv.addObject("projectNeedToProcess", projectInfoService.getProjectUserNeedToProcess(user));
        mv.addObject("projectPromotedProcessingHistoryMap", projectPromotedProcessingHistoryMap);
        mv.addObject("projectNeedToProcessHistoryMap", projectNeedToProcessHistoryMap);
    }

    @RequestMapping(value = "/project/projectManagement")
    public ModelAndView projectManagement(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("/project/projectManagement");
//        ModelAndView mv = new ModelAndView("/project/projectMnager");
        this.updateProjectInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/project/newProject")
    public ModelAndView newProject(@RequestParam(name = "title") String projectTitle,
                                    @RequestParam(name = "content") String projectContent,
                                    @RequestPart(name = "file") MultipartFile file,
                                    HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");

        String fileLoc = fileService.save(file);

        ProjectInfo project = new ProjectInfo();
        project.setContent(projectContent);
        project.setTitle(projectTitle);
        project.setPromoterId(user.getId());
        project.setFileLoc(fileLoc);
        projectInfoService.addNewProject(project);

        ModelAndView mv = new ModelAndView("/project/projectManagement");

        this.updateProjectInfo(mv, user);

        return mv;
    }

    @RequestMapping(value = "/project/processing")
    public ModelAndView processProject(@RequestParam(name = "isApprove") boolean isApprove,
                                        @RequestParam(name = "projectId") Integer projectId,
                                        @RequestParam(name = "reason") String reason,
                                        HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println("hello");
        projectInfoService.processProject(projectId, user, isApprove, reason);
        ModelAndView mv = new ModelAndView("/project/projectManagement");
        this.updateProjectInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/project/download")
    public ModelAndView download(@RequestParam(name = "fileLoc") String fileLoc,
                                 HttpServletResponse response) throws IOException {
        fileService.loadToServlet(fileLoc, response);
        return new ModelAndView("/project/projectManagement");
    }

    @RequestMapping(value = "/project/delete")
    public ModelAndView delete(@RequestParam(name = "projectId") Integer projectId,
                                 HttpServletResponse response) throws IOException {
        projectInfoService.deleteProject(projectId);
        return new ModelAndView("/project/projectManagement");
    }
}
