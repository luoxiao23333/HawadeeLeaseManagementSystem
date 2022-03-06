package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectInfoDao {
    List<ProjectWithUser> getProjectUserPromoted(Integer userId);
    List<ProjectWithUser> getProjectUserNeedToProcess(Integer userId);
    void addNewProject(ProjectInfo projectInfo);
    void forwardProject(Integer projectId, Integer dutyId);
    void finishProject(Integer projectId);
    void removeProject(Integer projectId);
}
