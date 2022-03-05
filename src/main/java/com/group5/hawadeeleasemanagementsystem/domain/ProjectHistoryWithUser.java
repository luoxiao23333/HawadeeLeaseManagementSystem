package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

@Data
public class ProjectHistoryWithUser {
    private ContractProcessingHistory history;
    private User user;

    public ContractProcessingHistory getHistory() {
        return history;
    }

    public void setHistory(ContractProcessingHistory history) {
        this.history = history;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}