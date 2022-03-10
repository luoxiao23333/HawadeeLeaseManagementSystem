package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Data
public class ProjectProcessingHistory {
    public static final Integer Approved = 1;
    public static final Integer Denied = 2;

    private Integer id;
    private Integer projectId;
    private String reason;
    private Date createDate;

    public String getFormattedCreateTime(){
        return DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA).format(this.getCreateDate());
    }

    public static Integer getApproved() {
        return Approved;
    }

    public static Integer getDenied() {
        return Denied;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;
}