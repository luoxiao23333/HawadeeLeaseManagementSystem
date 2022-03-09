package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.dao.userInfoDao;
import com.group5.hawadeeleasemanagementsystem.dao.userRelDao;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.domain.userInfo;
import com.group5.hawadeeleasemanagementsystem.domain.userRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userInfoService {
    userInfoDao userinfoDao;

    @Autowired
    void setUserInfoDao(userInfoDao userinfoDao) {this.userinfoDao = userinfoDao;}

    userRelDao userrelDao;

    @Autowired
    void setUserRelDao(userRelDao userrelDao) {this.userrelDao = userrelDao;}

    UserDao userDao;

    @Autowired
    void setUserDao(UserDao userDao) {this.userDao = userDao;}

    public User getColleague(Integer id){
        return  userDao.getUserById(userrelDao.getRel(id).getColleagueId());
    }

    public User getLeader(Integer id){
        return  userDao.getUserById(userrelDao.getRel(id).getLeaderId());
    }

    public User getSub(Integer id){
        return  userDao.getUserById(userrelDao.getRel(id).getSubordinateId());
    }

    public userInfo getUserInfo(Integer id){
        return userinfoDao.getUserInfo(id);
    }

    public userRel getUserRel(Integer id){ return userrelDao.getRel(id); }

    public void changeInfo(Integer id,Integer age,String school,Integer level,String selfIntro){
        userInfo userinfo = getUserInfo(id);
        if(userinfo != null){
            userinfoDao.updateAge(id,age);
            userinfoDao.updateLevel(id,level);
            userinfoDao.updateSchool(id,school);
            userinfoDao.updateSelfIntro(id,selfIntro);
        }
    }

    public void changeLeader(Integer id,Integer Lid){
        userrelDao.updateLeader(id,Lid);
    }

    public void changeColleague(Integer id,Integer Lid){
        userrelDao.updateColleague(id,Lid);
    }

    public void changeSub(Integer id,Integer Lid){
        userrelDao.updateSubordinate(id,Lid);
    }






}
