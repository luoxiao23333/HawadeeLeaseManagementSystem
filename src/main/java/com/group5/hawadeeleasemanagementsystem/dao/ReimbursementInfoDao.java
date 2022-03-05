package com.group5.hawadeeleasemanagementsystem.dao;

import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementWithUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReimbursementInfoDao {
    List<ReimbursementWithUser> getReimbursementUserPromoted(Integer userId);

    List<ReimbursementWithUser> getReimbursementUserNeedToProcess(Integer userId);

    void addNewReimbursement(ReimbursementInfo reimbursementInfo);

    void forwardReimbursement(Integer reimbursementInfoID, Integer dutyId);

    void finishReimbursement(Integer reimbursementInfoID);

    void removeReimbursement(Integer reimbursementInfoID);
}
