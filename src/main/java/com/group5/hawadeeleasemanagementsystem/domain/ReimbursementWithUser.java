package com.group5.hawadeeleasemanagementsystem.domain;

import lombok.Data;

@Data
public class ReimbursementWithUser {
    private ReimbursementInfo reimbursement;
    private User user;
}
