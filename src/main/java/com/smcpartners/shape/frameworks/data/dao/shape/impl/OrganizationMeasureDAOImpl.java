package com.smcpartners.shape.frameworks.data.dao.shape.impl;

import com.diffplug.common.base.Errors;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.entitymodel.shape.MeasureEntity;
import com.smcpartners.shape.frameworks.data.entitymodel.shape.OrganizationEntity;
import com.smcpartners.shape.frameworks.data.entitymodel.shape.OrganizationMeasureEntity;
import com.smcpartners.shape.frameworks.data.entitymodel.shape.UserEntity;
import com.smcpartners.shape.frameworks.data.exceptions.DataAccessException;
import com.smcpartners.shape.frameworks.producers.annotations.ShapeDatabase;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
@Stateless
public class OrganizationMeasureDAOImpl extends AbstractCrudDAO<OrganizationMeasureDTO, OrganizationMeasureEntity, Integer> implements OrganizationMeasureDAO {

    /**
     * Constructor
     */
    @Inject
    public OrganizationMeasureDAOImpl(@ShapeDatabase EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OrganizationMeasureDTO> findAllOrganizationMeasure() throws DataAccessException {
        try {
            List<OrganizationMeasureEntity> omLst = em.createNamedQuery("OrganizationMeasure.findAll").getResultList();

            List<OrganizationMeasureDTO> retLst = new ArrayList<>();
            if (omLst != null && omLst.size() > 0) {
                omLst.forEach(Errors.rethrow().wrap(om -> {
                    OrganizationMeasureDTO dto = this.mapEntityToDTO(om);
                    retLst.add(dto);
                }));
            }

            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationMeasure", e.getMessage(), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<OrganizationMeasureDTO> findAllOrganizationMeasureByOrgId(int orgId) throws DataAccessException {
        try {
            OrganizationEntity oe = em.find(OrganizationEntity.class, orgId);
            List<OrganizationMeasureEntity> omLst = em.createNamedQuery("OrganizationMeasure.findAllByOrgId")
                    .setParameter("org", oe)
                    .getResultList();

            List<OrganizationMeasureDTO> retLst = new ArrayList<>();
            if (omLst != null && omLst.size() > 0) {
                omLst.forEach(Errors.rethrow().wrap(om -> {
                    OrganizationMeasureDTO dto = this.mapEntityToDTO(om);
                    retLst.add(dto);
                }));
            }

            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationMeasureByOrgId", e.getMessage(), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected OrganizationMeasureEntity mapDtoToEntity(OrganizationMeasureEntity et, OrganizationMeasureDTO dto) {
        et.setAge1844(dto.getAge1844());
        et.setAge4564(dto.getAge4564());
        et.setAgeOver65(dto.getAgeOver65());
        et.setAgeUnder17(dto.getAgeUnder17());
        et.setDenominatorValue(dto.getDenominatorValue());
        et.setEthnicityHispanicLatino(dto.getEthnicityHispanicLatino());
        et.setEthnicityNotHispanicLatino(dto.getEthnicityNotHispanicLatino());
        et.setGenderFemale(dto.getGenderFemale());
        et.setGenderMale(dto.getGenderMale());
        et.setGenderOther(dto.getGenderOther());
        et.setId(dto.getId());
        et.setNumeratorValue(dto.getNumeratorValue());
        et.setRaceAfricanAmerican(dto.getRaceAfricanAmerican());
        et.setRaceAmericanIndian(dto.getRaceAmericanIndian());
        et.setRaceAsian(dto.getRaceAsian());
        et.setRaceNativeHawaiian(dto.getRaceNativeHawaiian());
        et.setRaceOther(dto.getRaceOther());
        et.setRaceWhite(dto.getRaceWhite());
        et.setRpDate(dto.getRpDate());

        // Organization
        OrganizationEntity org = em.find(OrganizationEntity.class, dto.getOrganizationId());
        et.setOrganizationByOrganizationId(org);

        // Measure
        MeasureEntity measure = em.find(MeasureEntity.class, dto.getMeasureId());
        et.setMeasureByMeasureId(measure);

        // User
        UserEntity user = em.find(UserEntity.class, dto.getUserId());
        et.setUserByUserId(user);

        // Return
        return et;
    }

    @Override
    protected Class<OrganizationMeasureEntity> getGenericEntityClass() throws Exception {
        return OrganizationMeasureEntity.class;
    }

    @Override
    protected OrganizationMeasureDTO mapEntityToDTO(OrganizationMeasureEntity e) throws Exception {
        OrganizationMeasureDTO d = new OrganizationMeasureDTO();
        d.setAge1844(e.getAge1844());
        d.setAge4564(e.getAge4564());
        d.setAgeOver65(e.getAgeOver65());
        d.setAgeUnder17(e.getAgeUnder17());
        d.setDenominatorValue(e.getDenominatorValue());
        d.setEthnicityHispanicLatino(e.getEthnicityHispanicLatino());
        d.setEthnicityNotHispanicLatino(e.getEthnicityNotHispanicLatino());
        d.setGenderFemale(d.getGenderMale());
        d.setGenderMale(e.getGenderMale());
        d.setGenderOther(e.getGenderOther());
        d.setId(e.getId());
        d.setNumeratorValue(e.getNumeratorValue());
        d.setRaceAfricanAmerican(e.getRaceAfricanAmerican());
        d.setRaceAmericanIndian(e.getRaceAmericanIndian());
        d.setRaceAsian(e.getRaceAsian());
        d.setRaceNativeHawaiian(e.getRaceNativeHawaiian());
        d.setRaceOther(e.getRaceOther());
        d.setRaceWhite(e.getRaceWhite());
        d.setId(e.getId());
        d.setMeasureId(e.getMeasureByMeasureId().getId());
        d.setOrganizationId(e.getOrganizationByOrganizationId().getId());
        d.setUserId(e.getUserByUserId().getId());
        return d;
    }
}
