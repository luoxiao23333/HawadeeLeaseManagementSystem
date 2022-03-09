package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.ContractHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractProcessingHistoryDao {
    List<ContractProcessingHistory> getContractProcessingHistoryByContractId(Integer contractId);
    void addNewRecord(Integer contractId, Integer status, String reason, Integer processUserId);
    List<ContractHistoryWithUser> getContractsWithUser(Integer contractId);
    List<ContractProcessingHistory> getAllHistory();
}
