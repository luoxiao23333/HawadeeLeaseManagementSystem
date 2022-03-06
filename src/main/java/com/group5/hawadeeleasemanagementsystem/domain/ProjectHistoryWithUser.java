package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ProjectHistoryWithUser {
    private ProjectProcessingHistory history;
    private User user;
}