package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ProjectInfoDao;
import com.group5.hawadeeleasemanagementsystem.dao.ProjectProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectProcessingHistory;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectProcessingHistoryService {
    private ProjectProcessingHistoryDao projectProcessingHistoryDao;
    @Autowired
    private void setUserDao(ProjectProcessingHistoryDao projectProcessingHistoryDao){
        this.projectProcessingHistoryDao = projectProcessingHistoryDao;
    }

    public List<ProjectProcessingHistory> getProjectProcessingHistoryByProject(ProjectInfo projectInfo){
        return projectProcessingHistoryDao.getProjectProcessingHistoryByProjectId(projectInfo.getId());
    }

    public Map<ProjectWithUser,List<ProjectHistoryWithUser>>
    getProjectProcessingHistoryMap(List<ProjectWithUser> projectInfoList){
        Map<ProjectWithUser, List<ProjectHistoryWithUser>> projectProcessingHistoryMap = new HashMap<>();
        for(ProjectWithUser projectInfo: projectInfoList){
            List<ProjectHistoryWithUser> historyList =
                    projectProcessingHistoryDao.getProjectsWithUser(projectInfo.getProject().getId());
            projectProcessingHistoryMap.put(projectInfo,historyList);
        }
        return projectProcessingHistoryMap;
    }

    void addNewRecord(Integer projectId, Integer status, String reason, Integer processUserId){
        projectProcessingHistoryDao.addNewRecord(projectId, status, reason, processUserId);
    }
}
