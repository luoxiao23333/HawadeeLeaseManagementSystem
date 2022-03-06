package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.userInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userInfoDao {
    userInfo getUserinfo(Integer userId);
    void updateSchool(Integer uId,String school);
    void updateAge(Integer uId,Integer age);
    void updateLevel(Integer uId,Integer level);
    void updateSelfIntro(Integer uId,String intro);
}
