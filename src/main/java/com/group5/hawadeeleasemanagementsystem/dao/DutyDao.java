package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.Duty;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DutyDao {
    List<Duty> getDutyByUserId(Integer userId);
}
