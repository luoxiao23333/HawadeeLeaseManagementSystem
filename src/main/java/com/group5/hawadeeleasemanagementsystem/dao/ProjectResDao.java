//package com.group5.hawadeeleasemanagementsystem.dao;
//
//import com.group5.hawadeeleasemanagementsystem.domain.ProjectRes;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//public interface ProjectResDao {
//    //    List<ProjectWithUser> getProjectUserPromoted(Integer userId);
////    List<ProjectWithUser> getProjectUserNeedToProcess(Integer userId);
//
//    //    List<ProjectWithUser> getProjectUserPromoted(Integer userId);
////    List<ProjectWithUser> getProjectUserNeedToProcess(Integer userId);
//    void addNewProjectRes(ProjectRes projectRes);
////    void forwardProject(Integer projectId, Integer dutyId);
////    void finishProject(Integer projectId);
////    void removeProject(Integer projectId);
//}



package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectResDao {

    void addNewProjectRes(ProjectRes projectRes);
    List<ProjectRes> getProjectRess();
    void setGrade(Integer projectResId,Integer grade);
    void setStatus(Integer projectResId,Integer status);
}
