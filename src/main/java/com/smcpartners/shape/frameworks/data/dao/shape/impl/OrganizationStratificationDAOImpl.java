package com.smcpartners.shape.frameworks.data.dao.shape.impl;

import com.diffplug.common.base.Errors;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationStratificationDAO;
import com.smcpartners.shape.frameworks.data.entitymodel.shape.OrganizationEntity;
import com.smcpartners.shape.frameworks.data.entitymodel.shape.OrganizationStratificationEntity;
import com.smcpartners.shape.frameworks.data.entitymodel.shape.UserEntity;
import com.smcpartners.shape.frameworks.data.exceptions.DataAccessException;
import com.smcpartners.shape.frameworks.producers.annotations.ShapeDatabase;
import com.smcpartners.shape.shared.dto.shape.OrganizationStratificationDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
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
public class OrganizationStratificationDAOImpl extends AbstractCrudDAO<OrganizationStratificationDTO, OrganizationStratificationEntity, Integer> implements OrganizationStratificationDAO {

    @Inject
    public OrganizationStratificationDAOImpl(@ShapeDatabase EntityManager em) {
        this.em = em;
    }

    @Override
    public List<OrganizationStratificationDTO> findAllOrganizationStratification() throws DataAccessException {
        try {
            List<OrganizationStratificationEntity> omLst = em.createNamedQuery("OrganizationStratification.findAll").getResultList();

            List<OrganizationStratificationDTO> retLst = new ArrayList<>();
            if (omLst != null && omLst.size() > 0) {
                omLst.forEach(Errors.rethrow().wrap(om -> {
                    OrganizationStratificationDTO dto = this.mapEntityToDTO(om);
                    retLst.add(dto);
                }));
            }

            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationStratification", e.getMessage(), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    public List<OrganizationStratificationDTO> findAllOrganizationStratificationByOrgId(int orgId) throws DataAccessException {
        try {
            OrganizationEntity oe = em.find(OrganizationEntity.class, orgId);
            List<OrganizationStratificationEntity> omLst = em.createNamedQuery("OrganizationStratification.findAllByOrgId")
                    .setParameter("org", oe)
                    .getResultList();

            List<OrganizationStratificationDTO> retLst = new ArrayList<>();
            if (omLst != null && omLst.size() > 0) {
                omLst.forEach(Errors.rethrow().wrap(om -> {
                    OrganizationStratificationDTO dto = this.mapEntityToDTO(om);
                    retLst.add(dto);
                }));
            }

            return retLst;
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationStratificationByOrgId", e.getMessage(), e);
            throw new DataAccessException(e);
        }
    }

    @Override
    protected OrganizationStratificationEntity mapDtoToEntity(OrganizationStratificationEntity et, OrganizationStratificationDTO dto) {
        et.setAge1844(dto.getAge1844());
        et.setAge4564(dto.getAge4564());
        et.setAgeOver65(dto.getAgeOver65());
        et.setAgeUnder17(dto.getAgeUnder17());
        et.setEthnicityHispanicLatino(dto.getEthnicityHispanicLatino());
        et.setEthnicityNotHispanicLatino(dto.getEthnicityNotHispanicLatino());
        et.setGenderFemale(dto.getGenderFemale());
        et.setGenderMale(dto.getGenderMale());
        et.setGenderOther(dto.getGenderOther());
        et.setId(dto.getId());
        et.setRaceAfricanAmerican(dto.getRaceAfricanAmerican());
        et.setRaceAmericanIndian(dto.getRaceAmericanIndian());
        et.setRaceAsian(dto.getRaceAsian());
        et.setRaceNativeHawaiian(dto.getRaceNativeHawaiian());
        et.setRaceOther(dto.getRaceOther());
        et.setRaceWhite(dto.getRaceWhite());
        et.setRpDate(new Date());

        // Look up organization
        OrganizationEntity oe = em.find(OrganizationEntity.class, dto.getOrganizationId());
        et.setOrganizationByOrganizationId(oe);

        // User
        UserEntity user = em.find(UserEntity.class, dto.getUserId());
        et.setUserByUserId(user);

        // Return
        return et;
    }

    @Override
    protected Class<OrganizationStratificationEntity> getGenericEntityClass() throws Exception {
        return OrganizationStratificationEntity.class;
    }

    @Override
    protected OrganizationStratificationDTO mapEntityToDTO(OrganizationStratificationEntity entity) throws Exception {
        OrganizationStratificationDTO dto = new OrganizationStratificationDTO();
        dto.setAge1844(entity.getAge1844());
        dto.setAge4564(entity.getAge4564());
        dto.setAgeOver65(entity.getAgeOver65());
        dto.setAgeUnder17(entity.getAgeUnder17());
        dto.setEthnicityHispanicLatino(entity.getEthnicityHispanicLatino());
        dto.setEthnicityNotHispanicLatino(entity.getEthnicityNotHispanicLatino());
        dto.setGenderFemale(entity.getGenderFemale());
        dto.setGenderMale(entity.getGenderMale());
        dto.setGenderOther(entity.getGenderOther());
        dto.setId(entity.getId());
        dto.setRaceAfricanAmerican(entity.getRaceAfricanAmerican());
        dto.setRaceAmericanIndian(entity.getRaceAmericanIndian());
        dto.setRaceAsian(entity.getRaceAsian());
        dto.setRaceNativeHawaiian(entity.getRaceNativeHawaiian());
        dto.setRaceOther(entity.getRaceOther());
        dto.setRaceWhite(entity.getRaceWhite());
        dto.setOrganizationId(entity.getOrganizationByOrganizationId().getId());
        dto.setUserId(entity.getUserByUserId().getId());
        dto.setRpDate(entity.getRpDate());
        return dto;
    }
}
