package com.smcpartners.shape.usecases.find_measures_by_id;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogActivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1.
 * <p>
 * Created by bhokanson on 12/1/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class FindMeasuresByIdServiceAdapter implements FindMeasuresByIdService {

    @Inject
    private Logger log;

    @EJB
    private MeasureDAO measureDAO;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindMeasuresByIdServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogActivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.DPH_USER, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<MeasureDTO> findMeasuresById(List<OrganizationMeasureDTO> orgM) throws UseCaseException {
        try {

            // create LinkedHashSet
            LinkedHashSet<MeasureDTO> newSet = new LinkedHashSet<>();
            //make sure list isn't empty
            if (orgM != null && orgM.size() > 0) {
                //loop through HashSet and find the Measure by Id, add to Measure List
                for (OrganizationMeasureDTO omd : orgM) {
                    //add to HashSet, which will not allow duplicates
                    newSet.add(measureDAO.findById(omd.getMeasureId()));
                }
                //convert HashSet to ArrayList to return (may not be necessary)
                return new ArrayList<>(newSet);
            } else {
                throw new Exception("List has no data");
            }
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findMeasuresById", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
