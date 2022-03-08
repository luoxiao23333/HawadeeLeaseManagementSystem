package com.group5.hawadeeleasemanagementsystem.domain.chartData;

import lombok.Data;
import org.apache.ibatis.javassist.compiler.ast.Pair;

import java.util.List;

@Data
public class LineChartData {
    private List<String> xAxisData;
    private List<LineData> dataList;
}

