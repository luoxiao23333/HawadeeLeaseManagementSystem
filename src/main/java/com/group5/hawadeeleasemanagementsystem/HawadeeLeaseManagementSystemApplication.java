package com.group5.hawadeeleasemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {
        "com.group5.hawadeeleasemanagementsystem.controller",
        "com.group5.hawadeeleasemanagementsystem.service",
        "com.group5.hawadeeleasemanagementsystem.domain",
        "com.group5.hawadeeleasemanagementsystem.dao",
        "com.group5.hawadeeleasemanagementsystem.filter"})
public class HawadeeLeaseManagementSystemApplication {

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    public static void main(String[] args) {
        SpringApplication.run(HawadeeLeaseManagementSystemApplication.class, args);
    }

}
