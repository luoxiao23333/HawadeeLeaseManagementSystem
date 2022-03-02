package com.group5.hawadeeleasemanagementsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group5.hawadeeleasemanagementsystem.entity.UserEntity;
import com.group5.hawadeeleasemanagementsystem.mapper.UserMapper;
import com.group5.hawadeeleasemanagementsystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
