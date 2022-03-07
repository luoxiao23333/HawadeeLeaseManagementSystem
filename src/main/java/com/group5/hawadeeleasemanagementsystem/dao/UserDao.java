package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getUserById(Integer uId);
    User verifyUser(User user);
    User getUserByDuty(Integer dutyId);
    void addUser(User user);
    void updatePassword(String username, String newPassword);
    void updatePhone(String username, String newPhone);
}