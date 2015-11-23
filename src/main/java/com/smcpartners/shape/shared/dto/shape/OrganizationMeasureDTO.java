package com.smcpartners.shape.shared.dto.shape;


import java.io.Serializable;
import java.util.Date;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/29/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
public class OrganizationMeasureDTO implements Serializable {
    private int id;
    private Integer numeratorValue;
    private Integer denominatorValue;
    private Date rpDate;
    private Integer genderMaleNum;
    private Integer genderMaleDen;
    private Integer genderFemaleNum;
    private Integer genderFemaleDen;
    private Integer genderOtherNum;
    private Integer genderOtherDen;
    private Integer ageUnder17Num;
    private Integer ageUnder17Den;
    private Integer age1844Num;
    private Integer age1844Den;
    private Integer age4564Num;
    private Integer age4564Den;
    private Integer ageOver65Num;
    private Integer ageOver65Den;
    private Integer ethnicityHispanicLatinoNum;
    private Integer ethnicityHispanicLatinoDen;
    private Integer ethnicityNotHispanicLatinoNum;
    private Integer ethnicityNotHispanicLatinoDen;
    private Integer raceAfricanAmericanNum;
    private Integer raceAfricanAmericanDen;
    private Integer raceAmericanIndianNum;
    private Integer raceAmericanIndianDen;
    private Integer raceAsianNum;
    private Integer raceAsianDen;
    private Integer raceNativeHawaiianNum;
    private Integer raceNativeHawaiianDen;
    private Integer raceWhiteNum;
    private Integer raceWhiteDen;
    private Integer raceOtherNum;
    private Integer raceOtherDen;
    private int organizationId;
    private int measureId;
    private String userId;

    public OrganizationMeasureDTO() {
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Integer getNumeratorValue() { return numeratorValue; }

    public void setNumeratorValue(Integer numeratorValue) { this.numeratorValue = numeratorValue; }

    public Integer getDenominatorValue() { return denominatorValue; }

    public void setDenominatorValue(Integer denominatorValue) { this.denominatorValue = denominatorValue; }

    public Date getRpDate() { return rpDate; }

    public void setRpDate(Date rpDate) { this.rpDate = rpDate; }

    public Integer getGenderMaleNum() { return genderMaleNum; }

    public void setGenderMaleNum(Integer genderMaleNum) { this.genderMaleNum = genderMaleNum; }

    public Integer getGenderMaleDen() { return genderMaleDen; }

    public void setGenderMaleDen(Integer genderMaleDen) { this.genderMaleDen = genderMaleDen; }

    public Integer getGenderFemaleNum() { return genderFemaleNum; }

    public void setGenderFemaleNum(Integer genderFemaleNum) { this.genderFemaleNum = genderFemaleNum; }

    public Integer getGenderFemaleDen() { return genderFemaleDen; }

    public void setGenderFemaleDen(Integer genderFemaleDen) { this.genderFemaleDen = genderFemaleDen; }

    public Integer getGenderOtherNum() { return genderOtherNum; }

    public void setGenderOtherNum(Integer genderOtherNum) { this.genderOtherNum = genderOtherNum;  }

    public Integer getGenderOtherDen() { return genderOtherDen; }

    public void setGenderOtherDen(Integer genderOtherDen) { this.genderOtherDen = genderOtherDen; }

    public Integer getAgeUnder17Num() { return ageUnder17Num; }

    public void setAgeUnder17Num(Integer ageUnder17Num) { this.ageUnder17Num = ageUnder17Num; }

    public Integer getAgeUnder17Den() { return ageUnder17Den; }

    public void setAgeUnder17Den(Integer ageUnder17Den) { this.ageUnder17Den = ageUnder17Den; }

    public Integer getAge1844Num() { return age1844Num; }

    public void setAge1844Num(Integer age1844Num) { this.age1844Num = age1844Num; }

    public Integer getAge1844Den() { return age1844Den; }

    public void setAge1844Den(Integer age1844Den) { this.age1844Den = age1844Den; }

    public Integer getAge4564Num() { return age4564Num; }

    public void setAge4564Num(Integer age4564Num) { this.age4564Num = age4564Num; }

    public Integer getAge4564Den() { return age4564Den; }

    public void setAge4564Den(Integer age4564Den) { this.age4564Den = age4564Den; }

    public Integer getAgeOver65Num() { return ageOver65Num; }

    public void setAgeOver65Num(Integer ageOver65Num) { this.ageOver65Num = ageOver65Num; }

    public Integer getAgeOver65Den() { return ageOver65Den; }

    public void setAgeOver65Den(Integer ageOver65Den) { this.ageOver65Den = ageOver65Den; }

    public Integer getEthnicityHispanicLatinoNum() { return ethnicityHispanicLatinoNum; }

    public void setEthnicityHispanicLatinoNum(Integer ethnicityHispanicLatinoNum) { this.ethnicityHispanicLatinoNum = ethnicityHispanicLatinoNum; }

    public Integer getEthnicityHispanicLatinoDen() { return ethnicityHispanicLatinoDen; }

    public void setEthnicityHispanicLatinoDen(Integer ethnicityHispanicLatinoDen) { this.ethnicityHispanicLatinoDen = ethnicityHispanicLatinoDen; }

    public Integer getEthnicityNotHispanicLatinoNum() { return ethnicityNotHispanicLatinoNum; }

    public void setEthnicityNotHispanicLatinoNum(Integer ethnicityNotHispanicLatinoNum) { this.ethnicityNotHispanicLatinoNum = ethnicityNotHispanicLatinoNum; }

    public Integer getEthnicityNotHispanicLatinoDen() { return ethnicityNotHispanicLatinoDen; }

    public void setEthnicityNotHispanicLatinoDen(Integer ethnicityNotHispanicLatinoDen) { this.ethnicityNotHispanicLatinoDen = ethnicityNotHispanicLatinoDen; }

    public Integer getRaceAfricanAmericanNum() { return raceAfricanAmericanNum; }

    public void setRaceAfricanAmericanNum(Integer raceAfricanAmericanNum) { this.raceAfricanAmericanNum = raceAfricanAmericanNum; }

    public Integer getRaceAfricanAmericanDen() { return raceAfricanAmericanDen; }

    public void setRaceAfricanAmericanDen(Integer raceAfricanAmericanDen) { this.raceAfricanAmericanDen = raceAfricanAmericanDen; }

    public Integer getRaceAmericanIndianNum() { return raceAmericanIndianNum; }

    public void setRaceAmericanIndianNum(Integer raceAmericanIndianNum) { this.raceAmericanIndianNum = raceAmericanIndianNum; }

    public Integer getRaceAmericanIndianDen() { return raceAmericanIndianDen; }

    public void setRaceAmericanIndianDen(Integer raceAmericanIndianDen) { this.raceAmericanIndianDen = raceAmericanIndianDen; }

    public Integer getRaceAsianNum() { return raceAsianNum; }

    public void setRaceAsianNum(Integer raceAsianNum) { this.raceAsianNum = raceAsianNum; }

    public Integer getRaceAsianDen() { return raceAsianDen; }

    public void setRaceAsianDen(Integer raceAsianDen) { this.raceAsianDen = raceAsianDen; }

    public Integer getRaceNativeHawaiianNum() { return raceNativeHawaiianNum; }

    public void setRaceNativeHawaiianNum(Integer raceNativeHawaiianNum) { this.raceNativeHawaiianNum = raceNativeHawaiianNum; }

    public Integer getRaceNativeHawaiianDen() { return raceNativeHawaiianDen; }

    public void setRaceNativeHawaiianDen(Integer raceNativeHawaiianDen) { this.raceNativeHawaiianDen = raceNativeHawaiianDen; }

    public Integer getRaceWhiteNum() { return raceWhiteNum; }

    public void setRaceWhiteNum(Integer raceWhiteNum) { this.raceWhiteNum = raceWhiteNum; }

    public Integer getRaceWhiteDen() { return raceWhiteDen; }

    public void setRaceWhiteDen(Integer raceWhiteDen) { this.raceWhiteDen = raceWhiteDen; }

    public Integer getRaceOtherNum() { return raceOtherNum; }

    public void setRaceOtherNum(Integer raceOtherNum) { this.raceOtherNum = raceOtherNum; }

    public Integer getRaceOtherDen() { return raceOtherDen; }

    public void setRaceOtherDen(Integer raceOtherDen) { this.raceOtherDen = raceOtherDen; }

    public int getOrganizationId() { return organizationId; }

    public void setOrganizationId(int organizationId) { this.organizationId = organizationId; }

    public int getMeasureId() { return measureId; }

    public void setMeasureId(int measureId) { this.measureId = measureId; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }
}
