package com.group5.hawadeeleasemanagementsystem.domain.chartData;

import lombok.Data;

import java.util.List;

@Data
public class LineData{
    private String label;
    private List<Number> dataset;
}