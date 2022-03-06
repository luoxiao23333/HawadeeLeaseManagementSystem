package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ReimbursementProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementHistoryWithUser;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementInfo;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementProcessingHistory;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReimbursementProcessingHistoryService {
    private ReimbursementProcessingHistoryDao reimbursementProcessingHistoryDao;

    @Autowired
    private void setUserDao(ReimbursementProcessingHistoryDao reimbursementProcessingHistoryDao) {
        this.reimbursementProcessingHistoryDao = reimbursementProcessingHistoryDao;
    }

    public List<ReimbursementProcessingHistory> getReimbursementProcessingHistoryByReimbursement(ReimbursementInfo reimbursementInfo) {
        return reimbursementProcessingHistoryDao.getReimbursementProcessingHistoryByReimbursementId(reimbursementInfo.getId());
    }

    public Map<ReimbursementWithUser, List<ReimbursementHistoryWithUser>>
    getReimbursementProcessingHistoryMap(List<ReimbursementWithUser> reimbursementInfoList) {
        Map<ReimbursementWithUser, List<ReimbursementHistoryWithUser>> reimbursementProcessingHistoryMap = new HashMap<>();
        for (ReimbursementWithUser reimbursementInfo : reimbursementInfoList) {
            List<ReimbursementHistoryWithUser> historyList =
                    reimbursementProcessingHistoryDao.getReimbursementsWithUser(reimbursementInfo.getReimbursement().getId());
            reimbursementProcessingHistoryMap.put(reimbursementInfo, historyList);
        }
        return reimbursementProcessingHistoryMap;
    }

    void addNewRecord(Integer reimbursementId, Integer status, String reason, Integer processUserId) {
        reimbursementProcessingHistoryDao.addNewRecord(reimbursementId, status, reason, processUserId);
    }
}



