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
    private ProjectInfoDao ProjectInfoDao;
    @Autowired
    private void setProjectInfoDao(ProjectInfoDao ProjectInfoDao){
        this.ProjectInfoDao = ProjectInfoDao;
    }

    private ProjectProcessingHistoryDao ProjectProcessingHistoryDao;
    @Autowired
    private void setProjectProcessingHistoryDao(ProjectProcessingHistoryDao ProjectProcessingHistoryDao){
        this.ProjectProcessingHistoryDao = ProjectProcessingHistoryDao;
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
        List<ProjectWithUser> ProjectsPromoted = ProjectInfoDao.getProjectUserPromoted(user.getId());
        System.out.println(user.getId());
        for(ProjectWithUser c: ProjectsPromoted){
            System.out.println(c.getProject().getCreateDate());
        }
        return ProjectInfoDao.getProjectUserPromoted(user.getId());
    }

    public List<ProjectWithUser> getProjectUserNeedToProcess(User user){
        return ProjectInfoDao.getProjectUserNeedToProcess(user.getId());
    }

    public void addNewProject(ProjectInfo ProjectInfo){
        User user = userDao.getUserByDuty(Duty.LawyerDirector.getId());
        ProjectInfo.setCurrentHandlerId(user.getId());
        ProjectInfoDao.addNewProject(ProjectInfo);
    }

    public void forwardProject(Integer ProjectId, Duty duty){
        ProjectInfoDao.forwardProject(ProjectId,duty.getId());
    }

    public void finishProject(Integer ProjectId){
        ProjectInfoDao.finishProject(ProjectId);
    }

    public void processProject(Integer ProjectId, User user, boolean isApproved, String reason){
        Integer status = isApproved? ProjectProcessingHistory.Approved : ProjectProcessingHistory.Denied;
        List<Duty> dutyList = dutyDao.getDutyByUserId(user.getId());

        if(isApproved){
            if(dutyList.contains(Duty.GeneralManager)){
                this.finishProject(ProjectId);
            }
            else if(dutyList.contains(Duty.LawyerDirector)){
                this.forwardProject(ProjectId, Duty.GeneralManager);
            }
        }else{
            if(dutyList.contains(Duty.GeneralManager)){
                this.forwardProject(ProjectId, Duty.LawyerDirector);
            }
            else if(dutyList.contains(Duty.LawyerDirector)){
                ProjectInfoDao.removeProject(ProjectId);
            }
        }

        ProjectProcessingHistoryDao.addNewRecord(ProjectId, status, reason, user.getId());
    }
}
