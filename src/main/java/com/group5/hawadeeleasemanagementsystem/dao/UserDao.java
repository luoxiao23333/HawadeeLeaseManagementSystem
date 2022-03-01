package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User verifyUser(User user);
    User getUserByDuty(Integer dutyId);
}
