package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Date;

/**
 * Responsible:</br>
 * 1. Entity</br>
 * <p>
 * Created by johndestefano on 10/28/15.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Entity
@javax.persistence.Table(name = "organization_measure", schema = "", catalog = "shape")
@NamedQueries({
        @NamedQuery(name = "OrganizationMeasure.findAll",
                query = "SELECT OBJECT(o) FROM OrganizationMeasureEntity o"),
        @NamedQuery(name = "OrganizationMeasure.findAllByOrgId",
                query = "SELECT OBJECT(o) FROM OrganizationMeasureEntity o " +
                        "WHERE o.organizationByOrganizationId = :org"),
        @NamedQuery(name = "OrganizationMeasure.findByMeasAndYear",
                query = "SElECT OBJECT(o) FROM OrganizationMeasureEntity o " +
                        "WHERE o.reportPeriodYear = :year AND o.measureByMeasureId = :meas"),
        @NamedQuery(name = "OrganizationMeasure.findByMeasYearOrg",
                query = "SElECT OBJECT(o) FROM OrganizationMeasureEntity o " +
                        "WHERE o.reportPeriodYear = :year AND o.measureByMeasureId = :meas " +
                        "AND o.organizationByOrganizationId = :org")
})
public class OrganizationMeasureEntity {
    private int id;
    private Integer numeratorValue;
    private Integer denominatorValue;
    private Date rpDate;
    private Integer genderMaleNum;
    private Integer genderMaleDen;
    private Integer genderFemaleNum;
    private Integer genderFemaleDen;
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
    private Integer reportPeriodQuarter;
    private Integer reportPeriodYear;
    private OrganizationEntity organizationByOrganizationId;
    private MeasureEntity measureByMeasureId;
    private UserEntity userByUserId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @javax.persistence.Column(name = "numerator_value", nullable = true, insertable = true, updatable = true)
    public Integer getNumeratorValue() {
        return numeratorValue;
    }

    public void setNumeratorValue(Integer numeratorValue) {
        this.numeratorValue = numeratorValue;
    }

    @Basic
    @javax.persistence.Column(name = "denominator_value", nullable = true, insertable = true, updatable = true)
    public Integer getDenominatorValue() {
        return denominatorValue;
    }

    public void setDenominatorValue(Integer denominatorValue) {
        this.denominatorValue = denominatorValue;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "rpDate", nullable = false, insertable = true, updatable = true)
    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    @Basic
    @javax.persistence.Column(name = "gender_male_num", nullable = true, insertable = true, updatable = true)
    public Integer getGenderMaleNum() {
        return genderMaleNum;
    }

    public void setGenderMaleNum(Integer genderMaleNum) {
        this.genderMaleNum = genderMaleNum;
    }

    @Basic
    @javax.persistence.Column(name = "gender_male_den", nullable = true, insertable = true, updatable = true)
    public Integer getGenderMaleDen() {
        return genderMaleDen;
    }

    public void setGenderMaleDen(Integer genderMaleDen) { this.genderMaleDen = genderMaleDen; }

    @Basic
    @javax.persistence.Column(name = "gender_female_num", nullable = true, insertable = true, updatable = true)
    public Integer getGenderFemaleNum() {
        return genderFemaleNum;
    }

    public void setGenderFemaleNum(Integer genderFemaleNum) {
        this.genderFemaleNum = genderFemaleNum;
    }

    @Basic
    @javax.persistence.Column(name = "gender_female_den", nullable = true, insertable = true, updatable = true)
    public Integer getGenderFemaleDen() {
        return genderFemaleDen;
    }

    public void setGenderFemaleDen(Integer genderFemaleDen) {
        this.genderFemaleDen = genderFemaleDen;
    }

    @Basic
    @javax.persistence.Column(name = "age_18_44_num", nullable = true, insertable = true, updatable = true)
    public Integer getAge1844Num() { return age1844Num; }

    public void setAge1844Num(Integer age1844Num) {
        this.age1844Num = age1844Num;
    }

    @Basic
    @javax.persistence.Column(name = "age_18_44_den", nullable = true, insertable = true, updatable = true)
    public Integer getAge1844Den() { return age1844Den; }

    public void setAge1844Den(Integer age1844Den) {
        this.age1844Den = age1844Den;
    }

    @Basic
    @javax.persistence.Column(name = "age_45_65_num", nullable = true, insertable = true, updatable = true)
    public Integer getAge4564Num() { return age4564Num; }

    public void setAge4564Num(Integer age4564Num) { this.age4564Num = age4564Num; }

    @Basic
    @javax.persistence.Column(name = "age_45_65_den", nullable = true, insertable = true, updatable = true)
    public Integer getAge4564Den() { return age4564Den; }

    public void setAge4564Den(Integer age4564Den) { this.age4564Den = age4564Den; }


    @Basic
    @javax.persistence.Column(name = "age_over_65_num", nullable = true, insertable = true, updatable = true)
    public Integer getAgeOver65Num() {
        return ageOver65Num;
    }

    public void setAgeOver65Num(Integer ageOver65Num) {
        this.ageOver65Num = ageOver65Num;
    }

    @Basic
    @javax.persistence.Column(name = "age_over_65_den", nullable = true, insertable = true, updatable = true)
    public Integer getAgeOver65Den() {
        return ageOver65Den;
    }

    public void setAgeOver65Den(Integer ageOver65Den) {
        this.ageOver65Den = ageOver65Den;
    }

    @Basic
    @javax.persistence.Column(name = "ethnicity_hispanic_latino_num", nullable = true, insertable = true, updatable = true)
    public Integer getEthnicityHispanicLatinoNum() {
        return ethnicityHispanicLatinoNum;
    }

    public void setEthnicityHispanicLatinoNum(Integer ethnicityHispanicLatinoNum) { this.ethnicityHispanicLatinoNum = ethnicityHispanicLatinoNum; }

    @Basic
    @javax.persistence.Column(name = "ethnicity_hispanic_latino_den", nullable = true, insertable = true, updatable = true)
    public Integer getEthnicityHispanicLatinoDen() {
        return ethnicityHispanicLatinoDen;
    }

    public void setEthnicityHispanicLatinoDen(Integer ethnicityHispanicLatinoDen) { this.ethnicityHispanicLatinoDen = ethnicityHispanicLatinoDen; }

    @Basic
    @javax.persistence.Column(name = "ethnicity_not_hispanic_latino_num", nullable = true, insertable = true, updatable = true)
    public Integer getEthnicityNotHispanicLatinoNum() {
        return ethnicityNotHispanicLatinoNum;
    }

    public void setEthnicityNotHispanicLatinoNum(Integer ethnicityNotHispanicLatinoNum) { this.ethnicityNotHispanicLatinoNum = ethnicityNotHispanicLatinoNum; }

    @Basic
    @javax.persistence.Column(name = "ethnicity_not_hispanic_latino_den", nullable = true, insertable = true, updatable = true)
    public Integer getEthnicityNotHispanicLatinoDen() {
        return ethnicityNotHispanicLatinoDen;
    }

    public void setEthnicityNotHispanicLatinoDen(Integer ethnicityNotHispanicLatinoDen) { this.ethnicityNotHispanicLatinoDen = ethnicityNotHispanicLatinoDen; }

    @Basic
    @javax.persistence.Column(name = "race_african_american_num", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAfricanAmericanNum() {
        return raceAfricanAmericanNum;
    }

    public void setRaceAfricanAmericanNum(Integer raceAfricanAmericanNum) { this.raceAfricanAmericanNum = raceAfricanAmericanNum; }

    @Basic
    @javax.persistence.Column(name = "race_african_american_den", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAfricanAmericanDen() {
        return raceAfricanAmericanDen;
    }

    public void setRaceAfricanAmericanDen(Integer raceAfricanAmericanDen) { this.raceAfricanAmericanDen = raceAfricanAmericanDen; }

    @Basic
    @javax.persistence.Column(name = "race_american_indian_num", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAmericanIndianNum() {
        return raceAmericanIndianNum;
    }

    public void setRaceAmericanIndianNum(Integer raceAmericanIndianNum) { this.raceAmericanIndianNum = raceAmericanIndianNum; }

    @Basic
    @javax.persistence.Column(name = "race_american_indian_den", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAmericanIndianDen() {
        return raceAmericanIndianDen;
    }

    public void setRaceAmericanIndianDen(Integer raceAmericanIndianDen) { this.raceAmericanIndianDen = raceAmericanIndianDen; }

    @Basic
    @javax.persistence.Column(name = "race_asian_num", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAsianNum() { return raceAsianNum; }

    public void setRaceAsianNum(Integer raceAsianNum) {
        this.raceAsianNum = raceAsianNum;
    }

    @Basic
    @javax.persistence.Column(name = "race_asian_den", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAsianDen() { return raceAsianDen; }

    public void setRaceAsianDen(Integer raceAsianDen) {
        this.raceAsianDen = raceAsianDen;
    }

    @Basic
    @javax.persistence.Column(name = "race_native_hawaiian_num", nullable = true, insertable = true, updatable = true)
    public Integer getRaceNativeHawaiianNum() {
        return raceNativeHawaiianNum;
    }

    public void setRaceNativeHawaiianNum(Integer raceNativeHawaiianNum) { this.raceNativeHawaiianNum = raceNativeHawaiianNum; }

    @Basic
    @javax.persistence.Column(name = "race_native_hawaiian_den", nullable = true, insertable = true, updatable = true)
    public Integer getRaceNativeHawaiianDen() { return raceNativeHawaiianDen; }

    public void setRaceNativeHawaiianDen(Integer raceNativeHawaiianDen) { this.raceNativeHawaiianDen = raceNativeHawaiianDen; }

    @Basic
    @javax.persistence.Column(name = "race_white_num", nullable = true, insertable = true, updatable = true)
    public Integer getRaceWhiteNum() {
        return raceWhiteNum;
    }

    public void setRaceWhiteNum(Integer raceWhiteNum) {
        this.raceWhiteNum = raceWhiteNum;
    }

    @Basic
    @javax.persistence.Column(name = "race_white_den", nullable = true, insertable = true, updatable = true)
    public Integer getRaceWhiteDen() {
        return raceWhiteDen;
    }

    public void setRaceWhiteDen(Integer raceWhiteDen) {
        this.raceWhiteDen = raceWhiteDen;
    }

    @Basic
    @javax.persistence.Column(name = "race_other_num", nullable = true, insertable = true, updatable = true)
    public Integer getRaceOtherNum() {
        return raceOtherNum;
    }

    public void setRaceOtherNum(Integer raceOtherNum) {
        this.raceOtherNum = raceOtherNum;
    }

    @Basic
    @javax.persistence.Column(name = "race_other_den", nullable = true, insertable = true, updatable = true)
    public Integer getRaceOtherDen() {
        return raceOtherDen;
    }

    public void setRaceOtherDen(Integer raceOtherDen) {
        this.raceOtherDen = raceOtherDen;
    }

    @Basic
    @javax.persistence.Column(name = "rep_per_qtr", nullable = true, insertable = true, updatable = true)
    public Integer getReportPeriodQuarter() {
        return reportPeriodQuarter;
    }

    public void setReportPeriodQuarter(Integer reportPeriodQuarter) { this.reportPeriodQuarter = reportPeriodQuarter; }

    @Basic
    @javax.persistence.Column(name = "rep_per_year", nullable = true, insertable = true, updatable = true)
    public Integer getReportPeriodYear() {
        return reportPeriodYear;
    }

    public void setReportPeriodYear(Integer reportPeriodYear) {
        this.reportPeriodYear = reportPeriodYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationMeasureEntity that = (OrganizationMeasureEntity) o;

        if (id != that.id) return false;
        if (numeratorValue != null ? !numeratorValue.equals(that.numeratorValue) : that.numeratorValue != null)
            return false;
        if (denominatorValue != null ? !denominatorValue.equals(that.denominatorValue) : that.denominatorValue != null)
            return false;
        if (rpDate != null ? !rpDate.equals(that.rpDate) : that.rpDate != null) return false;
        if (genderMaleNum != null ? !genderMaleNum.equals(that.genderMaleNum) : that.genderMaleNum != null) return false;
        if (genderMaleDen != null ? !genderMaleDen.equals(that.genderMaleDen) : that.genderMaleDen != null) return false;
        if (genderFemaleNum != null ? !genderFemaleNum.equals(that.genderFemaleNum) : that.genderFemaleNum != null) return false;
        if (genderFemaleDen != null ? !genderFemaleDen.equals(that.genderFemaleDen) : that.genderFemaleDen != null) return false;
        if (age1844Num != null ? !age1844Num.equals(that.age1844Num) : that.age1844Num != null) return false;
        if (age1844Den != null ? !age1844Den.equals(that.age1844Den) : that.age1844Den != null) return false;
        if (age4564Num != null ? !age4564Num.equals(that.age4564Num) : that.age4564Num != null) return false;
        if (age4564Den != null ? !age4564Den.equals(that.age4564Den) : that.age4564Den != null) return false;
        if (ageOver65Num != null ? !ageOver65Num.equals(that.ageOver65Num) : that.ageOver65Num != null) return false;
        if (ageOver65Den != null ? !ageOver65Den.equals(that.ageOver65Den) : that.ageOver65Den != null) return false;
        if (ethnicityHispanicLatinoNum != null ? !ethnicityHispanicLatinoNum.equals(that.ethnicityHispanicLatinoNum) : that.ethnicityHispanicLatinoNum != null)
            return false;
        if (ethnicityHispanicLatinoDen != null ? !ethnicityHispanicLatinoDen.equals(that.ethnicityHispanicLatinoDen) : that.ethnicityHispanicLatinoDen != null)
            return false;
        if (ethnicityNotHispanicLatinoNum != null ? !ethnicityNotHispanicLatinoNum.equals(that.ethnicityNotHispanicLatinoNum) : that.ethnicityNotHispanicLatinoNum != null)
            return false;
        if (ethnicityNotHispanicLatinoDen != null ? !ethnicityNotHispanicLatinoDen.equals(that.ethnicityNotHispanicLatinoDen) : that.ethnicityNotHispanicLatinoDen != null)
            return false;
        if (raceAfricanAmericanNum != null ? !raceAfricanAmericanNum.equals(that.raceAfricanAmericanNum) : that.raceAfricanAmericanNum != null)
            return false;
        if (raceAfricanAmericanDen != null ? !raceAfricanAmericanDen.equals(that.raceAfricanAmericanDen) : that.raceAfricanAmericanDen != null)
            return false;
        if (raceAmericanIndianNum != null ? !raceAmericanIndianNum.equals(that.raceAmericanIndianNum) : that.raceAmericanIndianNum != null)
            return false;
        if (raceAmericanIndianDen != null ? !raceAmericanIndianDen.equals(that.raceAmericanIndianDen) : that.raceAmericanIndianDen != null)
            return false;
        if (raceAsianNum != null ? !raceAsianNum.equals(that.raceAsianNum) : that.raceAsianNum != null) return false;
        if (raceAsianDen != null ? !raceAsianDen.equals(that.raceAsianDen) : that.raceAsianDen != null) return false;
        if (raceNativeHawaiianNum != null ? !raceNativeHawaiianNum.equals(that.raceNativeHawaiianNum) : that.raceNativeHawaiianNum != null)
            return false;
        if (raceNativeHawaiianDen != null ? !raceNativeHawaiianDen.equals(that.raceNativeHawaiianDen) : that.raceNativeHawaiianDen != null)
            return false;
        if (raceWhiteNum != null ? !raceWhiteNum.equals(that.raceWhiteNum) : that.raceWhiteNum != null) return false;
        if (raceWhiteDen != null ? !raceWhiteDen.equals(that.raceWhiteDen) : that.raceWhiteDen != null) return false;
        if (raceOtherNum != null ? !raceOtherNum.equals(that.raceOtherNum) : that.raceOtherNum != null) return false;
        if (raceOtherDen != null ? !raceOtherDen.equals(that.raceOtherDen) : that.raceOtherDen != null) return false;
        if (reportPeriodQuarter != null ? !reportPeriodQuarter.equals(that.reportPeriodQuarter) : that.reportPeriodQuarter != null) return false;
        if (reportPeriodYear != null ? !reportPeriodYear.equals(that.reportPeriodYear) : that.reportPeriodYear != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numeratorValue != null ? numeratorValue.hashCode() : 0);
        result = 31 * result + (denominatorValue != null ? denominatorValue.hashCode() : 0);
        result = 31 * result + (rpDate != null ? rpDate.hashCode() : 0);
        result = 31 * result + (genderMaleNum != null ? genderMaleNum.hashCode() : 0);
        result = 31 * result + (genderMaleDen != null ? genderMaleDen.hashCode() : 0);
        result = 31 * result + (genderFemaleNum != null ? genderFemaleNum.hashCode() : 0);
        result = 31 * result + (genderFemaleDen != null ? genderFemaleDen.hashCode() : 0);
        result = 31 * result + (age1844Num != null ? age1844Num.hashCode() : 0);
        result = 31 * result + (age1844Den != null ? age1844Den.hashCode() : 0);
        result = 31 * result + (age4564Num != null ? age4564Num.hashCode() : 0);
        result = 31 * result + (age4564Den != null ? age4564Den.hashCode() : 0);
        result = 31 * result + (ageOver65Num != null ? ageOver65Num.hashCode() : 0);
        result = 31 * result + (ageOver65Den != null ? ageOver65Den.hashCode() : 0);
        result = 31 * result + (ethnicityHispanicLatinoNum != null ? ethnicityHispanicLatinoNum.hashCode() : 0);
        result = 31 * result + (ethnicityHispanicLatinoDen != null ? ethnicityHispanicLatinoDen.hashCode() : 0);
        result = 31 * result + (ethnicityNotHispanicLatinoNum != null ? ethnicityNotHispanicLatinoNum.hashCode() : 0);
        result = 31 * result + (ethnicityNotHispanicLatinoDen != null ? ethnicityNotHispanicLatinoDen.hashCode() : 0);
        result = 31 * result + (raceAfricanAmericanNum != null ? raceAfricanAmericanNum.hashCode() : 0);
        result = 31 * result + (raceAfricanAmericanDen != null ? raceAfricanAmericanDen.hashCode() : 0);
        result = 31 * result + (raceAmericanIndianNum != null ? raceAmericanIndianNum.hashCode() : 0);
        result = 31 * result + (raceAmericanIndianDen != null ? raceAmericanIndianDen.hashCode() : 0);
        result = 31 * result + (raceAsianNum != null ? raceAsianNum.hashCode() : 0);
        result = 31 * result + (raceAsianDen != null ? raceAsianDen.hashCode() : 0);
        result = 31 * result + (raceNativeHawaiianNum != null ? raceNativeHawaiianNum.hashCode() : 0);
        result = 31 * result + (raceNativeHawaiianDen != null ? raceNativeHawaiianDen.hashCode() : 0);
        result = 31 * result + (raceWhiteNum != null ? raceWhiteNum.hashCode() : 0);
        result = 31 * result + (raceWhiteDen != null ? raceWhiteDen.hashCode() : 0);
        result = 31 * result + (raceOtherNum != null ? raceOtherNum.hashCode() : 0);
        result = 31 * result + (raceOtherDen != null ? raceOtherDen.hashCode() : 0);
        result = 31 * result + (reportPeriodQuarter != null ? reportPeriodQuarter.hashCode() : 0);
        result = 31 * result + (reportPeriodYear != null ? reportPeriodYear.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    public OrganizationEntity getOrganizationByOrganizationId() {
        return organizationByOrganizationId;
    }

    public void setOrganizationByOrganizationId(OrganizationEntity organizationByOrganizationId) {
        this.organizationByOrganizationId = organizationByOrganizationId;
    }

    @ManyToOne
    @JoinColumn(name = "measure_id", referencedColumnName = "id", nullable = false)
    public MeasureEntity getMeasureByMeasureId() {
        return measureByMeasureId;
    }

    public void setMeasureByMeasureId(MeasureEntity measureByMeasureId) {
        this.measureByMeasureId = measureByMeasureId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
