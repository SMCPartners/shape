package com.smcpartners.shape.shared.dto.shape.response;

import java.util.List;

/**
 * Created by bryanhokanson on 12/17/15.
 */
public class AppHistDemographicsDTO {

    private String nqfId;
    private String name;
    private String description;
    private int reportPeriodYear;
    private List<List<Object>> raceData;
    private List<List<Object>> ageData;
    private List<List<Object>> ethnicityData;
    private List<List<Object>> genderData;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReportPeriodYear() {
        return reportPeriodYear;
    }

    public void setReportPeriodYear(int reportPeriodYear) {
        this.reportPeriodYear = reportPeriodYear;
    }

    public List<List<Object>> getRaceData() {
        return raceData;
    }

    public void setRaceData(List<List<Object>> raceData) {
        this.raceData = raceData;
    }

    public List<List<Object>> getAgeData() {
        return ageData;
    }

    public void setAgeData(List<List<Object>> ageData) {
        this.ageData = ageData;
    }

    public List<List<Object>> getEthnicityData() {
        return ethnicityData;
    }

    public void setEthnicityData(List<List<Object>> ethnicityData) {
        this.ethnicityData = ethnicityData;
    }

    public List<List<Object>> getGenderData() {
        return genderData;
    }

    public void setGenderData(List<List<Object>> genderData) {
        this.genderData = genderData;
    }
}
