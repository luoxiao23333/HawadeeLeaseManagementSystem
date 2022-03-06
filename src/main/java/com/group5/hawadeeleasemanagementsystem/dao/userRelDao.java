package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.domain.userRel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userRelDao {
    userRel getUserRel(Integer userId);
    void updateColleague(Integer uId,Integer cId);
    void updateLeader(Integer uId,Integer lId);
    void updateSubordinate(Integer uId,Integer sId);
}
