package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
    private Integer amount;
    // private Integer type;
    private String content;
    private Date createDate;
    private String provFileLoc;
    private String approvalFileLoc;

    public String getFormattedCreateTime(){
        return DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA).format(this.getCreateDate());
    }
}
