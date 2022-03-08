package com.group5.hawadeeleasemanagementsystem.service;

import com.group5.hawadeeleasemanagementsystem.dao.ContractProcessingHistoryDao;
import com.group5.hawadeeleasemanagementsystem.domain.ContractProcessingHistory;
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

    public LineChartData getProcessingHistoryChartData() throws Exception {
        List<ContractProcessingHistory> historyList = contractProcessingHistoryDao.getAllHistory();
        Map<YearMonth, Integer> ApprovedCount = new HashMap<>();
        Map<YearMonth, Integer> DeniedCount = new HashMap<>();
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
}
