package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.ProjectInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectWithUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectInfoDao {
    List<ProjectWithUser> getProjectUserPromoted(Integer userId);
    List<ProjectWithUser> getProjectUserNeedToProcess(Integer userId);
    void addNewProject(ProjectInfo ProjectInfo);
    void forwardProject(Integer ProjectId, Integer dutyId);
    void finishProject(Integer ProjectId);
    void removeProject(Integer ProjectId);
}
