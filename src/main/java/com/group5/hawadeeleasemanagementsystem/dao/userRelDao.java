package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.userRel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userRelDao {
    userRel getRel(Integer userId);
    void updateColleague(Integer uId,Integer cId);
    void updateLeader(Integer uId,Integer lId);
    void updateSubordinate(Integer uId,Integer sId);
}
