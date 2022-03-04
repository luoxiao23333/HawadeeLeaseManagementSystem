package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractInfoDao {
    List<ContractWithUser> getContractUserPromoted(Integer userId);
    List<ContractWithUser> getContractUserNeedToProcess(Integer userId);
    void addNewContract(ContractInfo contractInfo);
    void forwardContract(Integer contractId, Integer dutyId);
    void finishContract(Integer contractId);
    void removeContract(Integer contractId);
}
