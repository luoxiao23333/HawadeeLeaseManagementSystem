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


    public void addNewProjectRes(ProjectRes projectRes){
        System.out.println("service");
        projectResDao.addNewProjectRes(projectRes);
    }


}
