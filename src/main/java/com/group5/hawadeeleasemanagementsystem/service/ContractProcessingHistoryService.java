package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ContractInfoDao;
import com.group5.hawadeeleasemanagementsystem.dao.ContractProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.domain.ContractHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ContractInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
import com.group5.hawadeeleasemanagementsystem.domain.ContractWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractProcessingHistoryService {
    private ContractProcessingHistoryDao contractProcessingHistoryDao;
    @Autowired
    private void setUserDao(ContractProcessingHistoryDao contractProcessingHistoryDao){
        this.contractProcessingHistoryDao = contractProcessingHistoryDao;
    }

    public List<ContractProcessingHistory> getContractProcessingHistoryByContract(ContractInfo contractInfo){
        return contractProcessingHistoryDao.getContractProcessingHistoryByContractId(contractInfo.getId());
    }

    public Map<ContractWithUser,List<ContractHistoryWithUser>>
    getContractProcessingHistoryMap(List<ContractWithUser> contractInfoList){
        Map<ContractWithUser, List<ContractHistoryWithUser>> contractProcessingHistoryMap = new HashMap<>();
        for(ContractWithUser contractInfo: contractInfoList){
            List<ContractHistoryWithUser> historyList =
                    contractProcessingHistoryDao.getContractsWithUser(contractInfo.getContract().getId());
            contractProcessingHistoryMap.put(contractInfo,historyList);
        }
        return contractProcessingHistoryMap;
    }

    void addNewRecord(Integer contractId, Integer status, String reason, Integer processUserId){
        contractProcessingHistoryDao.addNewRecord(contractId, status, reason, processUserId);
    }
}
