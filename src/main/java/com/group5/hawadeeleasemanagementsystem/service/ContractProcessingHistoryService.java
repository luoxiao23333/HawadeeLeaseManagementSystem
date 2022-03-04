package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ContractInfoDao;
import com.group5.hawadeeleasemanagementsystem.dao.ContractProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.domain.ContractHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ContractInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
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

    public Map<ContractInfo,List<ContractHistoryWithUser>>
    getContractProcessingHistoryMap(List<ContractInfo> contractInfoList){
        Map<ContractInfo, List<ContractHistoryWithUser>> contractProcessingHistoryMap = new HashMap<>();
        for(ContractInfo contractInfo: contractInfoList){
            List<ContractHistoryWithUser> historyList =
                    contractProcessingHistoryDao.getContractsWithUser(contractInfo.getId());
            contractProcessingHistoryMap.put(contractInfo,historyList);
            List<ContractHistoryWithUser> hus = contractProcessingHistoryDao.getContractsWithUser(contractInfo.getId());
        }
        return contractProcessingHistoryMap;
    }

    void addNewRecord(Integer contractId, Integer status, String reason, Integer processUserId){
        contractProcessingHistoryDao.addNewRecord(contractId, status, reason, processUserId);
    }
}
