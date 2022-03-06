package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.ProjectHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectProcessingHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectProcessingHistoryDao {
    List<ProjectProcessingHistory> getProjectProcessingHistoryByProjectId(Integer projectId);
    void addNewRecord(Integer projectId, Integer status, String reason, Integer processUserId);
    List<ProjectHistoryWithUser> getProjectsWithUser(Integer projectId);
}
