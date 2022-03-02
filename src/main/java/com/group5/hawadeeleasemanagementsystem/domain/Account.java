package com.group5.hawadeeleasemanagementsystem.domain;


import lombok.Data;

@Data
public class Account {
    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String password;
};