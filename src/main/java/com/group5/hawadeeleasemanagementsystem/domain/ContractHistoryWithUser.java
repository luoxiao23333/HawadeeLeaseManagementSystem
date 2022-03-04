package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ContractHistoryWithUser {
    private ContractProcessingHistory history;
    private User user;
}