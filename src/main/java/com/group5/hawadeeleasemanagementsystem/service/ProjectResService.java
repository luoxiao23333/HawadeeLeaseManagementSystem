package com.group5.hawadeeleasemanagementsystem.service;

//import com.group5.hawadeeleasemanagementsystem.dao.DutyDao;
//import com.group5.hawadeeleasemanagementsystem.dao.ProjectInfoDao;
//import com.group5.hawadeeleasemanagementsystem.dao.ProjectProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.dao.ProjectResDao;
//import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.ProjectRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectResService {
    private ProjectResDao projectResDao;

    @Autowired
    private void setProjectInfoDao(ProjectResDao projectResDao){
        this.projectResDao = projectResDao;
    }


    public void addNewProjectRes(ProjectRes projectRes){
        System.out.println("service");
        projectResDao.addNewProjectRes(projectRes);
    }

    public List<ProjectRes> getProjectRess() {
        List<ProjectRes> projectRess = projectResDao.getProjectRess();
        return projectRess;
    }

    public void setProjectGrade(Integer projectResId,Integer grade) {
        projectResDao.setGrade(projectResId, grade);
        projectResDao.setStatus(projectResId,2);
    }



}
