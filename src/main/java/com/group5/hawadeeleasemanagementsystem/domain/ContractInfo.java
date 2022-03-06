package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ContractInfo {

    public static final Integer PROCESSING = 1;
    public static final Integer FINISHED = 2;

    private Integer id;
    private Integer currentHandlerId;
    private Integer promoterId;
    private Integer status;
    private String title;
    private String content;
    private Date createDate;
    private String fileLoc;
}