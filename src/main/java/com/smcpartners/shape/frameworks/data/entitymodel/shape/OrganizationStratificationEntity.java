package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Date;

/**
 * Responsible:</br>
 * 1. </br>
 * <p>
 * <p>
 * Created by johndestefano on 10/28/15.
 * </p>
 * <p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@Entity
@Table(name = "organization_stratification", schema = "", catalog = "shape")
@NamedQueries({
        @NamedQuery(name = "OrganizationStratification.findAll",
                query = "SELECT OBJECT(o) FROM OrganizationStratificationEntity o"),
        @NamedQuery(name = "OrganizationStratification.findAllByOrgId",
                query = "SELECT OBJECT(o) FROM OrganizationStratificationEntity o " +
                        "WHERE o.organizationByOrganizationId = :org")
})
public class OrganizationStratificationEntity {
    private int id;
    private Integer genderMale;
    private Integer genderFemale;
    private Integer age1844;
    private Integer age4564;
    private Integer ageOver65;
    private Integer ethnicityHispanicLatino;
    private Integer ethnicityNotHispanicLatino;
    private Integer raceAfricanAmerican;
    private Integer raceAmericanIndian;
    private Integer raceAsian;
    private Integer raceNativeHawaiian;
    private Integer raceWhite;
    private Integer raceOther;
    private Integer totalPatients;
    private Integer totalVisits;
    private Integer patientsHypertension;
    private Integer patientsDiabetes;
    private Integer patientsPreDiabetes;
    private Integer patientsHighBp;
    private OrganizationEntity organizationByOrganizationId;
    private UserEntity userByUserId;
    private Date rpDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "gender_male", nullable = true, insertable = true, updatable = true)
    public Integer getGenderMale() {
        return genderMale;
    }

    public void setGenderMale(Integer genderMale) {
        this.genderMale = genderMale;
    }

    @Basic
    @Column(name = "gender_female", nullable = true, insertable = true, updatable = true)
    public Integer getGenderFemale() {
        return genderFemale;
    }

    public void setGenderFemale(Integer genderFemale) {
        this.genderFemale = genderFemale;
    }

    @Basic
    @Column(name = "age_18_44", nullable = true, insertable = true, updatable = true)
    public Integer getAge1844() {
        return age1844;
    }

    public void setAge1844(Integer age1844) {
        this.age1844 = age1844;
    }

    @Basic
    @Column(name = "age_45_64", nullable = true, insertable = true, updatable = true)
    public Integer getAge4564() {
        return age4564;
    }

    public void setAge4564(Integer age4564) {
        this.age4564 = age4564;
    }

    @Basic
    @Column(name = "age_over_65", nullable = true, insertable = true, updatable = true)
    public Integer getAgeOver65() {
        return ageOver65;
    }

    public void setAgeOver65(Integer ageOver65) {
        this.ageOver65 = ageOver65;
    }

    @Basic
    @Column(name = "ethnicity_hispanic_latino", nullable = true, insertable = true, updatable = true)
    public Integer getEthnicityHispanicLatino() {
        return ethnicityHispanicLatino;
    }

    public void setEthnicityHispanicLatino(Integer ethnicityHispanicLatino) {
        this.ethnicityHispanicLatino = ethnicityHispanicLatino;
    }

    @Basic
    @Column(name = "ethnicity_not_hispanic_latino", nullable = true, insertable = true, updatable = true)
    public Integer getEthnicityNotHispanicLatino() {
        return ethnicityNotHispanicLatino;
    }

    public void setEthnicityNotHispanicLatino(Integer ethnicityNotHispanicLatino) {
        this.ethnicityNotHispanicLatino = ethnicityNotHispanicLatino;
    }

    @Basic
    @Column(name = "race_african_american", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAfricanAmerican() {
        return raceAfricanAmerican;
    }

    public void setRaceAfricanAmerican(Integer raceAfricanAmerican) {
        this.raceAfricanAmerican = raceAfricanAmerican;
    }

    @Basic
    @Column(name = "race_american_indian", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAmericanIndian() {
        return raceAmericanIndian;
    }

    public void setRaceAmericanIndian(Integer raceAmericanIndian) {
        this.raceAmericanIndian = raceAmericanIndian;
    }

    @Basic
    @Column(name = "race_asian", nullable = true, insertable = true, updatable = true)
    public Integer getRaceAsian() {
        return raceAsian;
    }

    public void setRaceAsian(Integer raceAsian) {
        this.raceAsian = raceAsian;
    }

    @Basic
    @Column(name = "race_native_hawaiian", nullable = true, insertable = true, updatable = true)
    public Integer getRaceNativeHawaiian() {
        return raceNativeHawaiian;
    }

    public void setRaceNativeHawaiian(Integer raceNativeHawaiian) {
        this.raceNativeHawaiian = raceNativeHawaiian;
    }

    @Basic
    @Column(name = "race_white", nullable = true, insertable = true, updatable = true)
    public Integer getRaceWhite() {
        return raceWhite;
    }

    public void setRaceWhite(Integer raceWhite) {
        this.raceWhite = raceWhite;
    }

    @Basic
    @Column(name = "race_other", nullable = true, insertable = true, updatable = true)
    public Integer getRaceOther() {
        return raceOther;
    }

    public void setRaceOther(Integer raceOther) {
        this.raceOther = raceOther;
    }

    @Basic
    @Column(name = "total_patients", nullable = true, insertable = true, updatable = true)
    public Integer getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(Integer totalPatients) {
        this.totalPatients = totalPatients;
    }

    @Basic
    @Column(name = "total_visits", nullable = true, insertable = true, updatable = true)
    public Integer getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
    }

    @Basic
    @Column(name = "patients_hypertension", nullable = true, insertable = true, updatable = true)
    public Integer getPatientsHypertension() { return patientsHypertension; }

    public void setPatientsHypertension(Integer patientsHypertension) {
        this.patientsHypertension = patientsHypertension;
    }

    @Basic
    @Column(name = "patients_diabetes", nullable = true, insertable = true, updatable = true)
    public Integer getPatientsDiabetes() {
        return patientsDiabetes;
    }

    public void setPatientsDiabetes(Integer patientsDiabetes) {
        this.patientsDiabetes = patientsDiabetes;
    }

    @Basic
    @Column(name = "patients_pre_diabetes", nullable = true, insertable = true, updatable = true)
    public Integer getPatientsPreDiabetes() {
        return patientsPreDiabetes;
    }

    public void setPatientsPreDiabetes(Integer patientsPreDiabetes) {
        this.patientsPreDiabetes = patientsPreDiabetes;
    }

    @Basic
    @Column(name = "patients_highBp", nullable = true, insertable = true, updatable = true)
    public Integer getPatientsHighBp() {
        return patientsHighBp;
    }

    public void setPatientsHighBp(Integer patientsHighBp) {
        this.patientsHighBp = patientsHighBp;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationStratificationEntity that = (OrganizationStratificationEntity) o;

        if (id != that.id) return false;
        if (genderMale != null ? !genderMale.equals(that.genderMale) : that.genderMale != null) return false;
        if (genderFemale != null ? !genderFemale.equals(that.genderFemale) : that.genderFemale != null) return false;
        if (age1844 != null ? !age1844.equals(that.age1844) : that.age1844 != null) return false;
        if (age4564 != null ? !age4564.equals(that.age4564) : that.age4564 != null) return false;
        if (ageOver65 != null ? !ageOver65.equals(that.ageOver65) : that.ageOver65 != null) return false;
        if (ethnicityHispanicLatino != null ? !ethnicityHispanicLatino.equals(that.ethnicityHispanicLatino) : that.ethnicityHispanicLatino != null)
            return false;
        if (ethnicityNotHispanicLatino != null ? !ethnicityNotHispanicLatino.equals(that.ethnicityNotHispanicLatino) : that.ethnicityNotHispanicLatino != null)
            return false;
        if (raceAfricanAmerican != null ? !raceAfricanAmerican.equals(that.raceAfricanAmerican) : that.raceAfricanAmerican != null)
            return false;
        if (raceAmericanIndian != null ? !raceAmericanIndian.equals(that.raceAmericanIndian) : that.raceAmericanIndian != null)
            return false;
        if (raceAsian != null ? !raceAsian.equals(that.raceAsian) : that.raceAsian != null) return false;
        if (raceNativeHawaiian != null ? !raceNativeHawaiian.equals(that.raceNativeHawaiian) : that.raceNativeHawaiian != null)
            return false;
        if (raceWhite != null ? !raceWhite.equals(that.raceWhite) : that.raceWhite != null) return false;
        if (raceOther != null ? !raceOther.equals(that.raceOther) : that.raceOther != null) return false;
        if (totalPatients != null ? !totalPatients.equals(that.totalPatients) : that.totalPatients != null) return false;
        if (totalVisits != null ? !totalVisits.equals(that.totalVisits) : that.totalVisits != null) return false;
        if (patientsHypertension != null ? !patientsHypertension.equals(that.patientsHypertension) : that.patientsHypertension != null) return false;
        if (patientsDiabetes != null ? !patientsDiabetes.equals(that.patientsDiabetes) : that.patientsDiabetes != null) return false;
        if (patientsPreDiabetes != null ? !patientsPreDiabetes.equals(that.patientsPreDiabetes) : that.patientsPreDiabetes != null) return false;
        if (patientsHighBp != null ? !patientsHighBp.equals(that.patientsHighBp) : that.patientsHighBp != null) return false;




        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (genderMale != null ? genderMale.hashCode() : 0);
        result = 31 * result + (genderFemale != null ? genderFemale.hashCode() : 0);
        result = 31 * result + (age1844 != null ? age1844.hashCode() : 0);
        result = 31 * result + (age4564 != null ? age4564.hashCode() : 0);
        result = 31 * result + (ageOver65 != null ? ageOver65.hashCode() : 0);
        result = 31 * result + (ethnicityHispanicLatino != null ? ethnicityHispanicLatino.hashCode() : 0);
        result = 31 * result + (ethnicityNotHispanicLatino != null ? ethnicityNotHispanicLatino.hashCode() : 0);
        result = 31 * result + (raceAfricanAmerican != null ? raceAfricanAmerican.hashCode() : 0);
        result = 31 * result + (raceAmericanIndian != null ? raceAmericanIndian.hashCode() : 0);
        result = 31 * result + (raceAsian != null ? raceAsian.hashCode() : 0);
        result = 31 * result + (raceNativeHawaiian != null ? raceNativeHawaiian.hashCode() : 0);
        result = 31 * result + (raceWhite != null ? raceWhite.hashCode() : 0);
        result = 31 * result + (raceOther != null ? raceOther.hashCode() : 0);
        result = 31 * result + (totalPatients != null ? totalPatients.hashCode() : 0);
        result = 31 * result + (totalVisits != null ? totalVisits.hashCode() : 0);
        result = 31 * result + (patientsHypertension != null ? patientsHypertension.hashCode() : 0);
        result = 31 * result + (patientsDiabetes != null ? patientsDiabetes.hashCode() : 0);
        result = 31 * result + (patientsHighBp != null ? patientsHighBp.hashCode() : 0);
        result = 31 * result + (patientsPreDiabetes != null ? patientsPreDiabetes.hashCode() : 0);
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
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
