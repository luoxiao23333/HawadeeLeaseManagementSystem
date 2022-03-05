package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectProcessingHistory {
    public static final Integer Approved = 1;
    public static final Integer Denied = 2;

    private Integer id;
    private Integer ProjectId;
    private String reason;
    private Date createDate;
    private Integer status;

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
        return ProjectId;
    }

    public void setProjectId(Integer ProjectId) {
        this.ProjectId = ProjectId;
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
}