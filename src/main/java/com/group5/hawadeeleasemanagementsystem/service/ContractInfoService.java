package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ContractInfoDao;
import com.group5.hawadeeleasemanagementsystem.dao.ContractProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.dao.DutyDao;
import com.group5.hawadeeleasemanagementsystem.dao.UserDao;
import com.group5.hawadeeleasemanagementsystem.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractInfoService {
    private ContractInfoDao contractInfoDao;

    @Autowired
    private void setContractInfoDao(ContractInfoDao contractInfoDao) {
        this.contractInfoDao = contractInfoDao;
    }

    private ContractProcessingHistoryDao contractProcessingHistoryDao;

    @Autowired
    private void setContractProcessingHistoryDao(ContractProcessingHistoryDao contractProcessingHistoryDao) {
        this.contractProcessingHistoryDao = contractProcessingHistoryDao;
    }

    private UserDao userDao;

    @Autowired
    private void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private DutyDao dutyDao;

    @Autowired
    private void setDutyDao(DutyDao dutyDao) {
        this.dutyDao = dutyDao;
    }

    private FileService fileService;
    @Autowired
    private void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    public List<ContractWithUser> getContractUserPromoted(User user) {
        List<ContractWithUser> contractsPromoted = contractInfoDao.getContractUserPromoted(user.getId());
        System.out.println(user.getId());
        for (ContractWithUser c : contractsPromoted) {
            System.out.println(c.getContract().getCreateDate());
        }
        return contractInfoDao.getContractUserPromoted(user.getId());
    }

    public List<ContractWithUser> getContractUserNeedToProcess(User user) {
        return contractInfoDao.getContractUserNeedToProcess(user.getId());
    }

    public void addNewContract(ContractInfo contractInfo) {
        User user = userDao.getUserByDuty(Duty.LawyerDirector.getId());
        contractInfo.setCurrentHandlerId(user.getId());
        contractInfoDao.addNewContract(contractInfo);
    }

    public void forwardContract(Integer contractId, Duty duty) {
        contractInfoDao.forwardContract(contractId, duty.getId());
    }

    public void finishContract(Integer contractId) {
        contractInfoDao.finishContract(contractId);
    }

    public void processContract(Integer contractId, User user, boolean isApproved, String reason) {
        Integer status = isApproved ? ContractProcessingHistory.Approved : ContractProcessingHistory.Denied;
        List<Duty> dutyList = dutyDao.getDutyByUserId(user.getId());

        if (isApproved) {
            if (dutyList.contains(Duty.GeneralManager)) {
                this.finishContract(contractId);
            } else if (dutyList.contains(Duty.LawyerDirector)) {
                this.forwardContract(contractId, Duty.GeneralManager);
            }
        } else {
            if (dutyList.contains(Duty.GeneralManager)) {
                this.forwardContract(contractId, Duty.LawyerDirector);
            } else if (dutyList.contains(Duty.LawyerDirector)) {
                contractInfoDao.removeContract(contractId);
            }
        }

        contractProcessingHistoryDao.addNewRecord(contractId, status, reason, user.getId());
    }

    public void updateContentById(Integer contractId, String content) throws Exception {
        String fileName = this.contractInfoDao.getContractById(contractId).getContentLoc();
        this.fileService.saveTo(content, fileName);
    }

    public ContractInfo getContractById(Integer contractId){
        return this.contractInfoDao.getContractById(contractId);
    }
}
