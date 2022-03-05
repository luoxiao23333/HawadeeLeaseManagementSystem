package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.ContractHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementProcessingHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReimbursementProcessingHistoryDao {
    List<ReimbursementProcessingHistory> getReimbursementProcessingHistoryByReimbursementId(Integer ReimbursementId);
    void addNewRecord(Integer ReimbursementId, Integer status, String reason, Integer processUserId);
    List<ReimbursementHistoryWithUser> getReimbursementsWithUser(Integer contractId);
}
