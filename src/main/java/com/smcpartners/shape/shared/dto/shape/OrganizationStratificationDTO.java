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
public class OrganizationStratificationDTO implements Serializable {
    private int id;
    private int genderMale;
    private int genderFemale;
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
    private int totalPatients;
    private int totalVisits;
    private int patientsHypertension;
    private int patientsDiabetes;
    private int patientsPreDiabetes;
    private int patientsHighBp;
    private String userId;
    private Date rpDate;

    public OrganizationStratificationDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    public int getTotalPatients() {return totalPatients;}

    public void setTotalPatients(int totalPatients) {this.totalPatients = totalPatients;}

    public int getTotalVisits() {return totalVisits;}

    public void setTotalVisits(int totalVisits) {this.totalVisits = totalVisits;}

    public int getPatientsHypertension() {return patientsHypertension;}

    public void setPatientsHypertension(int patientsHypertension) {this.patientsHypertension = patientsHypertension;}

    public int getPatientsDiabetes() {return patientsDiabetes;}

    public void setPatientsDiabetes(int patientsDiabetes) {this.patientsDiabetes = patientsDiabetes;}

    public int getPatientsPreDiabetes() {return patientsPreDiabetes;}

    public void setPatientsPreDiabetes(int patientsPreDiabetes) {this.patientsPreDiabetes = patientsPreDiabetes;}

    public int getPatientsHighBp() {return patientsHighBp;}

    public void setPatientsHighBp(int patientsHighBp) {this.patientsHighBp = patientsHighBp;}
}
