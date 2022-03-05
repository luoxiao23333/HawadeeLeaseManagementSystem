package com.group5.hawadeeleasemanagementsystem.service;

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
    private ProjectProcessingHistoryDao ProjectProcessingHistoryDao;
    @Autowired
    private void setUserDao(ProjectProcessingHistoryDao ProjectProcessingHistoryDao){
        this.ProjectProcessingHistoryDao = ProjectProcessingHistoryDao;
    }

    public List<ProjectProcessingHistory> getProjectProcessingHistoryByProject(ProjectInfo ProjectInfo){
        return ProjectProcessingHistoryDao.getProjectProcessingHistoryByProjectId(ProjectInfo.getId());
    }

    public Map<ProjectWithUser,List<ProjectHistoryWithUser>>
    getProjectProcessingHistoryMap(List<ProjectWithUser> ProjectInfoList){
        Map<ProjectWithUser, List<ProjectHistoryWithUser>> ProjectProcessingHistoryMap = new HashMap<>();
        for(ProjectWithUser ProjectInfo: ProjectInfoList){
            List<ProjectHistoryWithUser> historyList =
                    ProjectProcessingHistoryDao.getProjectsWithUser(ProjectInfo.getProject().getId());
            ProjectProcessingHistoryMap.put(ProjectInfo,historyList);
        }
        return ProjectProcessingHistoryMap;
    }

    void addNewRecord(Integer ProjectId, Integer status, String reason, Integer processUserId){
        ProjectProcessingHistoryDao.addNewRecord(ProjectId, status, reason, processUserId);
    }
}
