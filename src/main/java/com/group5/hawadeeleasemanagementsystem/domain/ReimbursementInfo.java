package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ReimbursementInfo {

    public static final Integer PROCESSING = 1;
    public static final Integer FINISHED = 2;

    public static final Integer CAR = 1;
    public static final Integer HOUSE = 2;
    public static final Integer NORMAL = 3;

    private Integer id;
    private Integer currentHandlerId;
    private Integer promoterId;
    private Integer status;
    private String title;
    // private Integer type;
    private String content;
    private Date createDate;
    private String provFileLoc;
}
