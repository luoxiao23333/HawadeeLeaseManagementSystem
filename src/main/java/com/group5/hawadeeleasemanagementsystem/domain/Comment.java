package com.group5.hawadeeleasemanagementsystem.domain;

import com.group5.hawadeeleasemanagementsystem.domain.chartData.LineChartData;
import com.group5.hawadeeleasemanagementsystem.domain.chartData.LineData;
import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private Integer commentID;
    private String nickname;
    private String content;
}