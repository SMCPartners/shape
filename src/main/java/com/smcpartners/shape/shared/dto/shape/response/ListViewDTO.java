package com.smcpartners.shape.shared.dto.shape.response;

import java.util.List;

/**
 * Created by bryanhokanson on 12/14/15.
 */
public class ListViewDTO {

    private String nqfId;
    private String name;
    private String desciption;
    private int numeratorValue;
    private int denominatorValue;
    private List<List<Object>> chartData;
    private ChartOptionsDTO chartOptions;

    public String getNqfId() {
        return nqfId;
    }

    public void setNqfId(String nqfId) {
        this.nqfId = nqfId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public int getNumeratorValue() { return numeratorValue; }

    public void setNumeratorValue(int numeratorValue) {
        this.numeratorValue = numeratorValue;
    }

    public int getDenominatorValue() {
        return denominatorValue;
    }

    public void setDenominatorValue(int denominatorValue) {
        this.denominatorValue = denominatorValue;
    }

    public List<List<Object>> getChartData() {
        return chartData;
    }

    public void setChartData(List<List<Object>> chartData) {
        this.chartData = chartData;
    }

    public ChartOptionsDTO getChartOptions() {
        return chartOptions;
    }

    public void setChartOptions(ChartOptionsDTO chartOptions) {
        this.chartOptions = chartOptions;
    }
}
