package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

@Data
public class ProjectWithUser {
    private ProjectInfo Project;
    private User user;

    public ProjectInfo getProject() {
        return Project;
    }

    public void setProject(ProjectInfo Project) {
        this.Project = Project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
