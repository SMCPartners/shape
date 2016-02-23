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
    private boolean primaryCarePractice;
    private boolean fqhcLookALike;
    private boolean comHealthCenter;
    private boolean multiSpecPractice;
    private boolean pracConsortium;
    private boolean ambulatoryClinic;
    private boolean hmo;
    private boolean aco;
    private boolean pcmh;
    private boolean otherOrgDescrip;
    private boolean physicians;
    private boolean nursePrac;
    private boolean rn;
    private boolean lpn;
    private boolean pa;
    private boolean medicalAssist;
    private boolean residents;
    private boolean interns;
    private boolean communityHealthWorkers;
    private boolean trainedMotivationalInterview;
    private Integer medicarePercent;
    private Integer medicaidPercent;
    private Integer hmoPercent;
    private Integer ppoPercent;
    private Integer uninsuredSelfPercent;
    private Integer privatePercent;
    private String vendor;
    private String product;
    private String versionEHR;
    private boolean completeCEHRT;
    private boolean patientPortal;
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

    public boolean isPrimaryCarePractice() {
        return primaryCarePractice;
    }

    public void setPrimaryCarePractice(boolean primaryCarePractice) {
        this.primaryCarePractice = primaryCarePractice;
    }

    public boolean isFqhcLookALike() {
        return fqhcLookALike;
    }

    public void setFqhcLookALike(boolean fqhcLookALike) {
        this.fqhcLookALike = fqhcLookALike;
    }

    public boolean isComHealthCenter() {
        return comHealthCenter;
    }

    public void setComHealthCenter(boolean comHealthCenter) {
        this.comHealthCenter = comHealthCenter;
    }

    public boolean isMultiSpecPractice() {
        return multiSpecPractice;
    }

    public void setMultiSpecPractice(boolean multiSpecPractice) {
        this.multiSpecPractice = multiSpecPractice;
    }

    public boolean isPracConsortium() {
        return pracConsortium;
    }

    public void setPracConsortium(boolean pracConsortium) {
        this.pracConsortium = pracConsortium;
    }

    public boolean isAmbulatoryClinic() {
        return ambulatoryClinic;
    }

    public void setAmbulatoryClinic(boolean ambulatoryClinic) {
        this.ambulatoryClinic = ambulatoryClinic;
    }

    public boolean isHmo() {
        return hmo;
    }

    public void setHmo(boolean hmo) {
        this.hmo = hmo;
    }

    public boolean isAco() {
        return aco;
    }

    public void setAco(boolean aco) {
        this.aco = aco;
    }

    public boolean isPcmh() {
        return pcmh;
    }

    public void setPcmh(boolean pcmh) {
        this.pcmh = pcmh;
    }

    public boolean isOtherOrgDescrip() {
        return otherOrgDescrip;
    }

    public void setOtherOrgDescrip(boolean otherOrgDescrip) {
        this.otherOrgDescrip = otherOrgDescrip;
    }

    public boolean isPhysicians() {
        return physicians;
    }

    public void setPhysicians(boolean physicians) {
        this.physicians = physicians;
    }

    public boolean isNursePrac() {
        return nursePrac;
    }

    public void setNursePrac(boolean nursePrac) {
        this.nursePrac = nursePrac;
    }

    public boolean isRn() {
        return rn;
    }

    public void setRn(boolean rn) {
        this.rn = rn;
    }

    public boolean isLpn() {
        return lpn;
    }

    public void setLpn(boolean lpn) {
        this.lpn = lpn;
    }

    public boolean isPa() {
        return pa;
    }

    public void setPa(boolean pa) {
        this.pa = pa;
    }

    public boolean isMedicalAssist() {
        return medicalAssist;
    }

    public void setMedicalAssist(boolean medicalAssist) {
        this.medicalAssist = medicalAssist;
    }

    public boolean isResidents() {
        return residents;
    }

    public void setResidents(boolean residents) {
        this.residents = residents;
    }

    public boolean isInterns() {
        return interns;
    }

    public void setInterns(boolean interns) {
        this.interns = interns;
    }

    public boolean isCommunityHealthWorkers() {
        return communityHealthWorkers;
    }

    public void setCommunityHealthWorkers(boolean communityHealthWorkers) {
        this.communityHealthWorkers = communityHealthWorkers;
    }

    public boolean isTrainedMotivationalInterview() {
        return trainedMotivationalInterview;
    }

    public void setTrainedMotivationalInterview(boolean trainedMotivationalInterview) {
        this.trainedMotivationalInterview = trainedMotivationalInterview;
    }

    public Integer getMedicarePercent() {
        return medicarePercent;
    }

    public void setMedicarePercent(Integer medicarePercent) {
        this.medicarePercent = medicarePercent;
    }

    public Integer getMedicaidPercent() {
        return medicaidPercent;
    }

    public void setMedicaidPercent(Integer medicaidPercent) {
        this.medicaidPercent = medicaidPercent;
    }

    public Integer getHmoPercent() {
        return hmoPercent;
    }

    public void setHmoPercent(Integer hmoPercent) {
        this.hmoPercent = hmoPercent;
    }

    public Integer getPpoPercent() {
        return ppoPercent;
    }

    public void setPpoPercent(Integer ppoPercent) {
        this.ppoPercent = ppoPercent;
    }

    public Integer getUninsuredSelfPercent() {
        return uninsuredSelfPercent;
    }

    public void setUninsuredSelfPercent(Integer uninsuredSelfPercent) {
        this.uninsuredSelfPercent = uninsuredSelfPercent;
    }

    public Integer getPrivatePercent() {
        return privatePercent;
    }

    public void setPrivatePercent(Integer privatePercent) {
        this.privatePercent = privatePercent;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersionEHR() {
        return versionEHR;
    }

    public void setVersionEHR(String versionEHR) {
        this.versionEHR = versionEHR;
    }

    public boolean isCompleteCEHRT() {
        return completeCEHRT;
    }

    public void setCompleteCEHRT(boolean completeCEHRT) {
        this.completeCEHRT = completeCEHRT;
    }

    public boolean isPatientPortal() {
        return patientPortal;
    }

    public void setPatientPortal(boolean patientPortal) {
        this.patientPortal = patientPortal;
    }
}


