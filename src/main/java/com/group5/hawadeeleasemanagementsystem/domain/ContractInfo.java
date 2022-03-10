package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Data
public class ContractInfo {

    public static final Integer PROCESSING = 1;
    public static final Integer FINISHED = 2;

    private Integer id;
    private Integer currentHandlerId;
    private Integer promoterId;
    private Integer status;
    private String title;
    private String contentLoc;
    private Date createDate;
    private String fileLoc;

    public String getFormattedCreateTime(){
        return DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA).format(this.getCreateDate());
    }
}