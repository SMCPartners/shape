package com.smcpartners.shape.shared.dto.shape.response;

import java.util.List;

/**
 * Created by bryanhokanson on 12/15/15.
 */
public class TrendChartDTO {
    private String name;
    private String description;
    private String numeratorDescription;
    private String denominatorDescription;
    private List<List<String>> trendChart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumeratorDescription() {
        return numeratorDescription;
    }

    public void setNumeratorDescription(String numeratorDescription) {
        this.numeratorDescription = numeratorDescription;
    }

    public String getDenominatorDescription() {
        return denominatorDescription;
    }

    public void setDenominatorDescription(String denominatorDescription) {
        this.denominatorDescription = denominatorDescription;
    }

    public List<List<String>> getTrendChart() {
        return trendChart;
    }

    public void setTrendChart(List<List<String>> trendChart) {
        this.trendChart = trendChart;
    }
}
