package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    private void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public User login(User user){
        user = userDao.verifyUser(user);
        return user;
    }
}
