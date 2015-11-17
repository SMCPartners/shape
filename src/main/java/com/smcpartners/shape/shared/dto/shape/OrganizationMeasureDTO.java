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
public class OrganizationMeasureDTO implements Serializable{
    private int id;
    private int numeratorValue;
    private int denominatorValue;
    private Date rpDate;
    private int genderMale;
    private int genderFemale;
    private int genderOther;
    private int ageUnder17;
    private int age1844;
    private int age4564;
    private int ageOver65;
    private int ethnicityHispanicLatino;
    private int ethnicityNotHispanicLatino;
    private int raceAfricanAmerican;
    private int raceAmericanIndian;
    private int raceAsian;
    private int raceNativeHawaiian;
    private int raceWhite;
    private int raceOther;
    private int organizationId;
    private int measureId;
    private String userId;

    public OrganizationMeasureDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    public int getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(int genderMale) {
        this.genderMale = genderMale;
    }

    public int getGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(int genderFemale) {
        this.genderFemale = genderFemale;
    }

    public int getGenderOther() {
        return genderOther;
    }

    public void setGenderOther(int genderOther) {
        this.genderOther = genderOther;
    }

    public int getAgeUnder17() {
        return ageUnder17;
    }

    public void setAgeUnder17(int ageUnder17) {
        this.ageUnder17 = ageUnder17;
    }

    public int getAge1844() {
        return age1844;
    }

    public void setAge1844(int age1844) {
        this.age1844 = age1844;
    }

    public int getAge4564() {
        return age4564;
    }

    public void setAge4564(int age4564) {
        this.age4564 = age4564;
    }

    public int getAgeOver65() {
        return ageOver65;
    }

    public void setAgeOver65(int ageOver65) {
        this.ageOver65 = ageOver65;
    }

    public int getEthnicityHispanicLatino() {
        return ethnicityHispanicLatino;
    }

    public void setEthnicityHispanicLatino(int ethnicityHispanicLatino) {
        this.ethnicityHispanicLatino = ethnicityHispanicLatino;
    }

    public int getEthnicityNotHispanicLatino() {
        return ethnicityNotHispanicLatino;
    }

    public void setEthnicityNotHispanicLatino(int ethnicityNotHispanicLatino) {
        this.ethnicityNotHispanicLatino = ethnicityNotHispanicLatino;
    }

    public int getRaceAfricanAmerican() {
        return raceAfricanAmerican;
    }

    public void setRaceAfricanAmerican(int raceAfricanAmerican) {
        this.raceAfricanAmerican = raceAfricanAmerican;
    }

    public int getRaceAmericanIndian() {
        return raceAmericanIndian;
    }

    public void setRaceAmericanIndian(int raceAmericanIndian) {
        this.raceAmericanIndian = raceAmericanIndian;
    }

    public int getRaceAsian() {
        return raceAsian;
    }

    public void setRaceAsian(int raceAsian) {
        this.raceAsian = raceAsian;
    }

    public int getRaceNativeHawaiian() {
        return raceNativeHawaiian;
    }

    public void setRaceNativeHawaiian(int raceNativeHawaiian) {
        this.raceNativeHawaiian = raceNativeHawaiian;
    }

    public int getRaceWhite() {
        return raceWhite;
    }

    public void setRaceWhite(int raceWhite) {
        this.raceWhite = raceWhite;
    }

    public int getRaceOther() {
        return raceOther;
    }

    public void setRaceOther(int raceOther) {
        this.raceOther = raceOther;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getMeasureId() {
        return measureId;
    }

    public void setMeasureId(int measureId) {
        this.measureId = measureId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
