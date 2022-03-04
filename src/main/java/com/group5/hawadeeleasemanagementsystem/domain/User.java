package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class User {
    private Integer id;
    private String password;
    private String name;
    private String phone;
}