package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.ContractInfo;
import com.group5.hawadeeleasemanagementsystem.domain.Duty;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractInfoDao {
    List<ContractInfo> getContractUserPromoted(Integer userId);
    List<ContractInfo> getContractUserNeedToProcess(Integer userId);
    void addNewContract(ContractInfo contractInfo);
    void forwardContract(Integer contractId, Integer dutyId);
    void finishContract(Integer contractId);
    void removeContract(Integer contractId);
}
