package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ProjectInfoDao;
import com.group5.hawadeeleasemanagementsystem.dao.ProjectProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.dao.DutyDao;
import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInfoService {
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private void setProjectInfoDao(ProjectInfoDao projectInfoDao){
        this.projectInfoDao = projectInfoDao;
    }

    private ProjectProcessingHistoryDao projectProcessingHistoryDao;
    @Autowired
    private void setProjectProcessingHistoryDao(ProjectProcessingHistoryDao projectProcessingHistoryDao){
        this.projectProcessingHistoryDao = projectProcessingHistoryDao;
    }

    private UserDao userDao;
    @Autowired
    private void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    private DutyDao dutyDao;
    @Autowired
    private void setDutyDao(DutyDao dutyDao){
        this.dutyDao = dutyDao;
    }

    public List<ProjectWithUser> getProjectUserPromoted(User user){
        List<ProjectWithUser> projectsPromoted = projectInfoDao.getProjectUserPromoted(user.getId());
        System.out.println(user.getId());
        for(ProjectWithUser c: projectsPromoted){
            System.out.println(c.getProject().getCreateDate());
        }
        return projectInfoDao.getProjectUserPromoted(user.getId());
    }

    public List<ProjectWithUser> getProjectUserNeedToProcess(User user){
        return projectInfoDao.getProjectUserNeedToProcess(user.getId());
    }

    public void addNewProject(ProjectInfo projectInfo){
        User user = userDao.getUserByDuty(Duty.LawyerDirector.getId());
        projectInfo.setCurrentHandlerId(user.getId());
        projectInfoDao.addNewProject(projectInfo);
    }

    public void forwardProject(Integer projectId, Duty duty){
        projectInfoDao.forwardProject(projectId,duty.getId());
    }

    public void finishProject(Integer projectId){
        projectInfoDao.finishProject(projectId);
    }

    public void deleteProject(Integer projectId){
        projectInfoDao.removeProject(projectId);
    }

    public void processProject(Integer projectId, User user, boolean isApproved, String reason){
        Integer status = isApproved? ProjectProcessingHistory.Approved : ProjectProcessingHistory.Denied;
        List<Duty> dutyList = dutyDao.getDutyByUserId(user.getId());

        if(isApproved){
            if(dutyList.contains(Duty.GeneralManager)){
                this.finishProject(projectId);
            }
            else if(dutyList.contains(Duty.LawyerDirector)){
                this.forwardProject(projectId, Duty.GeneralManager);
            }
        }else{
//            if(dutyList.contains(Duty.GeneralManager)){
//                this.forwardProject(projectId, Duty.LawyerDirector);
//            }
//            else if(dutyList.contains(Duty.LawyerDirector)){
//                projectInfoDao.removeProject(projectId);
//            }
            this.finishProject(projectId);
        }

        projectProcessingHistoryDao.addNewRecord(projectId, status, reason, user.getId());
    }
}
