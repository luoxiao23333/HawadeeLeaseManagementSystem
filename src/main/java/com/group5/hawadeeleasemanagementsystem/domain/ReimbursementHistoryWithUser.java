package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

@Data
public class ReimbursementHistoryWithUser {
    private ReimbursementProcessingHistory history;
    private User user;
}