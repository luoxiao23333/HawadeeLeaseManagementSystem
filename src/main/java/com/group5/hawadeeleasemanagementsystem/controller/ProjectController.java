package com.group5.hawadeeleasemanagementsystem.controller;

import com.group5.hawadeeleasemanagementsystem.domain.ProjectHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.service.ProjectInfoService;
import com.group5.hawadeeleasemanagementsystem.service.ProjectProcessingHistoryService;
import com.group5.hawadeeleasemanagementsystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectController {
    private ProjectInfoService ProjectInfoService;
    @Autowired
    private void setUserService(ProjectInfoService ProjectInfoService){
        this.ProjectInfoService = ProjectInfoService;
    }

    private ProjectProcessingHistoryService ProjectInfoHistoryService;
    @Autowired
    private void setUserService(ProjectProcessingHistoryService ProjectInfoHistoryService){
        this.ProjectInfoHistoryService = ProjectInfoHistoryService;
    }

    private FileService fileService;
    @Autowired
    private void setFileService(FileService fileService){
        this.fileService = fileService;
    }

    private void updateProjectInfo(ModelAndView mv, User user){
        List<ProjectWithUser> ProjectsPromoted = ProjectInfoService.getProjectUserPromoted(user);
        List<ProjectWithUser> ProjectsNeedToProcess = ProjectInfoService.getProjectUserNeedToProcess(user);
        Map<ProjectWithUser, List<ProjectHistoryWithUser>> ProjectPromotedProcessingHistoryMap =
                ProjectInfoHistoryService.getProjectProcessingHistoryMap(ProjectsPromoted);
        Map<ProjectWithUser, List<ProjectHistoryWithUser>> ProjectNeedToProcessHistoryMap =
                ProjectInfoHistoryService.getProjectProcessingHistoryMap(ProjectsNeedToProcess);

        mv.addObject("ProjectPromoted", ProjectsPromoted);
        mv.addObject("ProjectNeedToProcess", ProjectInfoService.getProjectUserNeedToProcess(user));
        mv.addObject("ProjectPromotedProcessingHistoryMap", ProjectPromotedProcessingHistoryMap);
        mv.addObject("ProjectNeedToProcessHistoryMap", ProjectNeedToProcessHistoryMap);
    }

    @RequestMapping(value = "/Project/ProjectManagement")
    public ModelAndView ProjectManagement(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("/Project/ProjectManagement");
        this.updateProjectInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/Project/newProject")
    public ModelAndView newProject(@RequestParam(name = "title") String ProjectTitle,
                                    @RequestParam(name = "content") String ProjectContent,
                                    @RequestPart(name = "file") MultipartFile file,
                                    HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");

        String fileLoc = fileService.save(file);

        ProjectInfo Project = new ProjectInfo();
        Project.setContent(ProjectContent);
        Project.setTitle(ProjectTitle);
        Project.setPromoterId(user.getId());
        Project.setFileLoc(fileLoc);
        ProjectInfoService.addNewProject(Project);

        ModelAndView mv = new ModelAndView("/Project/ProjectManagement");

        this.updateProjectInfo(mv, user);

        return mv;
    }

    @RequestMapping(value = "/Project/processing")
    public ModelAndView processProject(@RequestParam(name = "isApprove") boolean isApprove,
                                        @RequestParam(name = "ProjectId") Integer ProjectId,
                                        @RequestParam(name = "reason") String reason,
                                        HttpSession session){
        User user = (User) session.getAttribute("user");
        ProjectInfoService.processProject(ProjectId, user, isApprove, reason);
        ModelAndView mv = new ModelAndView("/Project/ProjectManagement");
        this.updateProjectInfo(mv, user);
        return mv;
    }

    @RequestMapping(value = "/Project/download")
    public ModelAndView download(@RequestParam(name = "fileLoc") String fileLoc,
                                 HttpServletResponse response) throws IOException {
        fileService.loadToServlet(fileLoc, response);
        return new ModelAndView("/Project/ProjectManagement");
    }
}
