package com.smcpartners.shape.shared.dto.shape.response;

import java.util.List;

/**
 * Created by bryanhokanson on 12/14/15.
 */
public class ListViewDTO {

    private String nqfId;
    private String name;
    private String desciption;
    private int reportPeriodQuarter;
    private int numeratorValue;
    private int denominatorValue;
    private List<List<String>> chartData;
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

    public int getReportPeriodQuarter() {
        return reportPeriodQuarter;
    }

    public void setReportPeriodQuarter(int reportPeriodQuarter) {
        this.reportPeriodQuarter = reportPeriodQuarter;
    }

    public int getNumeratorValue() {
        return numeratorValue;
    }

    public void setNumeratorValue(int numeratorValue) {
        this.numeratorValue = numeratorValue;
    }

    public int getDenominatorValue() {
        return denominatorValue;
    }

    public void setDenominatorValue(int denominatorValue) {
        this.denominatorValue = denominatorValue;
    }

    public List<List<String>> getChartData() {
        return chartData;
    }

    public void setChartData(List<List<String>> chartData) {
        this.chartData = chartData;
    }

    public ChartOptionsDTO getChartOptions() {
        return chartOptions;
    }

    public void setChartOptions(ChartOptionsDTO chartOptions) {
        this.chartOptions = chartOptions;
    }
}
