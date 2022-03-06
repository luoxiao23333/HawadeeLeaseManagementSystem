package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ProjectHistoryWithUser {
    private ProjectProcessingHistory history;
    private User user;

    public ProjectProcessingHistory getHistory() {
        return history;
    }

    public void setHistory(ProjectProcessingHistory history) {
        this.history = history;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}