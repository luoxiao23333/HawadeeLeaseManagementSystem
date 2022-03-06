package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ReimbursementProcessingHistory {
    public static final Integer Approved = 1;
    public static final Integer Denied = 2;

    private Integer id;
    private Integer reimbursementId;
    private String reason;
    private Date createDate;
    private Integer status;
}
