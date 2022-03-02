package com.group5.hawadeeleasemanagementsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.group5.hawadeeleasemanagementsystem.controller",
        "com.group5.hawadeeleasemanagementsystem.service",
        "com.group5.hawadeeleasemanagementsystem.entity",})
@MapperScan("com.group5.hawadeeleasemanagementsystem.mapper")
public class HawadeeLeaseManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HawadeeLeaseManagementSystemApplication.class, args);
    }

}
