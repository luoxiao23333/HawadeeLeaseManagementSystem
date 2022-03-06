package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectInfo {

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

    public static Integer getPROCESSING() {
        return PROCESSING;
    }

    public static Integer getFINISHED() {
        return FINISHED;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrentHandlerId() {
        return currentHandlerId;
    }

    public void setCurrentHandlerId(Integer currentHandlerId) {
        this.currentHandlerId = currentHandlerId;
    }

    public Integer getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(Integer promoterId) {
        this.promoterId = promoterId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFileLoc() {
        return fileLoc;
    }

    public void setFileLoc(String fileLoc) {
        this.fileLoc = fileLoc;
    }
}