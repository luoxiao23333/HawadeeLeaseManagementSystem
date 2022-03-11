package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.*;
import com.group5.hawadeeleasemanagementsystem.domain.*;
import com.group5.hawadeeleasemanagementsystem.domain.chartData.LineChartData;
import com.group5.hawadeeleasemanagementsystem.domain.chartData.LineData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ReimbursementInfoService {
    private ReimbursementInfoDao reimbursementInfoDao;

    @Autowired
    private void setReimbursementDao(ReimbursementInfoDao reimbursementInfoDao) {
        this.reimbursementInfoDao = reimbursementInfoDao;
    }

    private ReimbursementProcessingHistoryDao reimbursementProcessingHistoryDao;

    @Autowired
    private void setReimbursementProcessingHistoryDao(ReimbursementProcessingHistoryDao reimbursementProcessingHistoryDao) {
        this.reimbursementProcessingHistoryDao = reimbursementProcessingHistoryDao;
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

    public List<ReimbursementWithUser> getReimbursementUserPromoted(User user) {
        List<ReimbursementWithUser> reimbursementsPromoted = reimbursementInfoDao.getReimbursementUserPromoted(user.getId());
        System.out.println(user.getId());
        for (ReimbursementWithUser c : reimbursementsPromoted) {
            System.out.println(c.getReimbursement().getCreateDate());
        }
        return reimbursementInfoDao.getReimbursementUserPromoted(user.getId());
    }

    public List<ReimbursementWithUser> getReimbursementUserNeedToProcess(User user) {
        return reimbursementInfoDao.getReimbursementUserNeedToProcess(user.getId());
    }

    public void addNewReimbursement(ReimbursementInfo reimbursementInfo) {
        User user = userDao.getUserByDuty(Duty.BusinessStuff.getId());
        reimbursementInfo.setCurrentHandlerId(user.getId());
        reimbursementInfoDao.addNewReimbursement(reimbursementInfo);
    }

    public void forwardReimbursement(Integer reimbursementId, Duty duty) {
        reimbursementInfoDao.forwardReimbursement(reimbursementId, duty.getId());
    }

    public void finishReimbursement(Integer reimbursementId) {
        reimbursementInfoDao.finishReimbursement(reimbursementId);
    }

    public void processReimbursement(Integer reimbursementId, User user, boolean isApproved, String reason) {
        Integer status = isApproved ? ReimbursementProcessingHistory.Approved : ReimbursementProcessingHistory.Denied;
        List<Duty> dutyList = dutyDao.getDutyByUserId(user.getId());

        if (isApproved) {
            if (dutyList.contains(Duty.GeneralManager)) {
                this.finishReimbursement(reimbursementId);
            } else if (dutyList.contains(Duty.BusinessStuff)) {
                this.forwardReimbursement(reimbursementId, Duty.GeneralManager);
            }
        } else {
            if (dutyList.contains(Duty.GeneralManager)) {
                this.forwardReimbursement(reimbursementId, Duty.BusinessStuff);
            } else if (dutyList.contains(Duty.BusinessStuff)) {
                reimbursementInfoDao.removeReimbursement(reimbursementId);
            }
        }

        reimbursementProcessingHistoryDao.addNewRecord(reimbursementId, status, reason, user.getId());
    }
}
