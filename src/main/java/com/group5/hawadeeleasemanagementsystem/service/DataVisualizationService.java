package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ContractProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.dao.DutyDao;
import com.group5.hawadeeleasemanagementsystem.dao.ReimbursementInfoDao;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
import com.group5.hawadeeleasemanagementsystem.domain.Duty;
import com.group5.hawadeeleasemanagementsystem.domain.ReimbursementInfo;
import com.group5.hawadeeleasemanagementsystem.domain.User;
import com.group5.hawadeeleasemanagementsystem.domain.chartData.LineChartData;
import com.group5.hawadeeleasemanagementsystem.domain.chartData.LineData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class DataVisualizationService {
    ContractProcessingHistoryDao contractProcessingHistoryDao;
    @Autowired
    private void setContractProcessingHistoryDao(ContractProcessingHistoryDao contractProcessingHistoryDao){
        this.contractProcessingHistoryDao = contractProcessingHistoryDao;
    }

    ReimbursementInfoDao reimbursementInfoDao;
    @Autowired
    private void setContractProcessingHistoryDao(ReimbursementInfoDao reimbursementInfoDao){
        this.reimbursementInfoDao = reimbursementInfoDao;
    }

    DutyDao dutyDao;
    @Autowired
    private void setDutyDao(DutyDao dutyDao){
        this.dutyDao = dutyDao;
    }

    public boolean isLegalUser(User user){
        return this.dutyDao.getDutyByUserId(user.getId()).get(0).equals(Duty.GeneralManager);
    }

    public LineChartData getProcessingHistoryChartData() throws Exception {
        List<ContractProcessingHistory> historyList = contractProcessingHistoryDao.getAllHistory();
        Map<YearMonth, Integer> ApprovedCount = new TreeMap<>();
        Map<YearMonth, Integer> DeniedCount = new TreeMap<>();
        for(ContractProcessingHistory history: historyList){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(history.getCreateDate());
            final int year = calendar.get(Calendar.YEAR);
            final int month = calendar.get(Calendar.MONTH) + 1;
            YearMonth date = YearMonth.of(year, month);
            if(Objects.equals(history.getStatus(), ContractProcessingHistory.Approved)){
                ApprovedCount.put(date, ApprovedCount.getOrDefault(date, 0) + 1);
                DeniedCount.putIfAbsent(date, 0);
            }else if(Objects.equals(history.getStatus(), ContractProcessingHistory.Denied)){
                DeniedCount.put(date, DeniedCount.getOrDefault(date, 0) + 1);
                ApprovedCount.putIfAbsent(date, 0);
            }else{
                throw new Exception("Unsupported Status");
            }
        }

        LineChartData lineChartData = new LineChartData();
        List<String> xAxisData = new ArrayList<>();
        List<LineData> dataList = new ArrayList<>();
        LineData approveLineData = new LineData();
        approveLineData.setLabel("Approved");
        List<Number> approvedDataset = new ArrayList<>();
        for(Map.Entry<YearMonth, Integer> record: ApprovedCount.entrySet()){
            YearMonth date = record.getKey();
            xAxisData.add(date.getYear() + " " + date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
            approvedDataset.add(record.getValue());
        }
        approveLineData.setDataset(approvedDataset);
        lineChartData.setXAxisData(xAxisData);
        dataList.add(approveLineData);

        LineData deniedLineData = new LineData();
        deniedLineData.setLabel("Denied");
        List<Number> deniedDataset = new ArrayList<>();
        for(Map.Entry<YearMonth, Integer> record: DeniedCount.entrySet()){
            deniedDataset.add(record.getValue());
        }
        deniedLineData.setDataset(deniedDataset);
        dataList.add(deniedLineData);

        lineChartData.setDataList(dataList);
        return lineChartData;
    }

    public LineChartData getReimbursementChartData(){
        List<ReimbursementInfo> reimbursementInfoList = this.reimbursementInfoDao.getAllReimbursement();
        LineChartData lineChartData = new LineChartData();
        List<String> xAxisData = new ArrayList<>();

        LineData currentDataList = new LineData();
        LineData totalDataList = new LineData();
        currentDataList.setLabel("当前报销金额");
        totalDataList.setLabel("累积报销金额");

        Map<YearMonth, List<ReimbursementInfo>> dateReimbursementMap = new TreeMap<>();
        for(ReimbursementInfo reimbursementInfo: reimbursementInfoList){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(reimbursementInfo.getCreateDate());
            YearMonth yearMonth = YearMonth.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
            if(dateReimbursementMap.containsKey(yearMonth)){
                dateReimbursementMap.get(yearMonth).add(reimbursementInfo);
            }else{
                List<ReimbursementInfo> list = new ArrayList<>();
                list.add(reimbursementInfo);
                dateReimbursementMap.put(yearMonth, list);
            }
        }

        List<Number> currentDataset = new ArrayList<>();
        List<Number> totalDataset = new ArrayList<>();
        int total = 0;
        for(Map.Entry<YearMonth, List<ReimbursementInfo>> record:dateReimbursementMap.entrySet()){
            YearMonth date = record.getKey();
            xAxisData.add(date.getYear() + " " + date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
            int current = 0;
            for(ReimbursementInfo reimbursementInfo: record.getValue()){
                current += reimbursementInfo.getAmount();
            }
            total += current;
            currentDataset.add(current);
            totalDataset.add(total);
        }

        currentDataList.setDataset(currentDataset);
        totalDataList.setDataset(totalDataset);

        lineChartData.setXAxisData(xAxisData);
        lineChartData.setDataList(Arrays.asList(currentDataList, totalDataList));
        return lineChartData;
    }
}
