package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

@Data
public class ProjectWithUser {
    private ProjectInfo project;

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
}
