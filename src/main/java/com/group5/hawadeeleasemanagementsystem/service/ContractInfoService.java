package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ContractInfoDao;
import com.group5.hawadeeleasemanagementsystem.dao.ContractProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.dao.DutyDao;
import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.ContractInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
import com.group5.hawadeeleasemanagementsystem.domain.Duty;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractInfoService {
    private ContractInfoDao contractInfoDao;
    @Autowired
    private void setContractInfoDao(ContractInfoDao contractInfoDao){
        this.contractInfoDao = contractInfoDao;
    }

    private ContractProcessingHistoryDao contractProcessingHistoryDao;
    @Autowired
    private void setContractProcessingHistoryDao(ContractProcessingHistoryDao contractProcessingHistoryDao){
        this.contractProcessingHistoryDao = contractProcessingHistoryDao;
    }

    private UserDao userDao;
    @Autowired
    private void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    private DutyDao dutyDao;
    @Autowired
    private void setDutyDao(DutyDao dutyDao){
        this.dutyDao = dutyDao;
    }

    public List<ContractInfo> getContractUserPromoted(User user){
        return contractInfoDao.getContractUserPromoted(user.getId());
    }

    public List<ContractInfo> getContractUserNeedToProcess(User user){
        return contractInfoDao.getContractUserNeedToProcess(user.getId());
    }

    public void addNewContract(ContractInfo contractInfo){
        User user = userDao.getUserByDuty(Duty.LawyerDirector.getId());
        contractInfo.setCurrentHandlerId(user.getId());
        contractInfoDao.addNewContract(contractInfo);
    }

    public void forwardContract(Integer contractId, Duty duty){
        contractInfoDao.forwardContract(contractId,duty.getId());
    }

    public void finishContract(Integer contractId){
        contractInfoDao.finishContract(contractId);
    }

    public void processContract(Integer contractId, User user, boolean isApproved, String reason){
        Integer status = isApproved? ContractProcessingHistory.Approved : ContractProcessingHistory.Denied;
        List<Duty> dutyList = dutyDao.getDutyByUserId(user.getId());

        if(isApproved){
            if(dutyList.contains(Duty.GeneralManager)){
                this.finishContract(contractId);
            }
            else if(dutyList.contains(Duty.LawyerDirector)){
                this.forwardContract(contractId, Duty.GeneralManager);
            }
        }else{
            if(dutyList.contains(Duty.GeneralManager)){
                this.forwardContract(contractId, Duty.LawyerDirector);
            }
            else if(dutyList.contains(Duty.LawyerDirector)){
                contractInfoDao.removeContract(contractId);
            }
        }

        contractProcessingHistoryDao.addNewRecord(contractId, status, reason, user.getId());
    }
}
