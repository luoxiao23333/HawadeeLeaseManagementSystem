package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Client {
    private Integer id;
    private String name;
    private String phone;
    private String email;
}
