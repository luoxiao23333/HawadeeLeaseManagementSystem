package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Data
public class ContractProcessingHistory {
    public static final Integer Approved = 1;
    public static final Integer Denied = 2;

    private Integer id;
    private Integer contractId;
    private String reason;
    private Date createDate;
    private Integer status;

    public String getFormattedCreateTime(){
        return DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA).format(this.getCreateDate());
    }
}