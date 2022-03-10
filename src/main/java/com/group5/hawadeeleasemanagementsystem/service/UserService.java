package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    private void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public User getUserById(Integer id){
        return userDao.getUserById(id);
    }

    public User login(String name, String password){
        User user = new User();
        user.setPassword(password);
        user.setName(name);
        user = userDao.verifyUser(user);
        return user;
    }

    public boolean addUser(String name, String password, String phone){
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setPassword(password);
        try {
            userDao.addUser(user);
        }catch (DataAccessException e){
            return true;
        }
        return false;
    }

    public void resetPassword(String username, String newPassword){
        userDao.updatePassword(username, newPassword);
    }

    public void resetPhone(String username, String newPhone){
        userDao.updatePhone(username, newPhone);
    }
}