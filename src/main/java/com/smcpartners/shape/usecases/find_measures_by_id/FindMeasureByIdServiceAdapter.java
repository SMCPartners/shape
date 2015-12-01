package com.smcpartners.shape.usecases.find_measures_by_id;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.usecases.UseCaseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class FindMeasureByIdServiceAdapter implements FindMeasureByIdService {

    @Inject
    private Logger log;

    @EJB
    private MeasureDAO measureDAO;

    @EJB
    private UserDAO userDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public FindMeasureByIdServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<MeasureDTO> findMeasureById(List<OrganizationMeasureDTO> orgM) throws UseCaseException {
        try {

            // Get user and find security role
            UserDTO user = userDAO.findById(requestScopedUserId.getRequestUserId());
            SecurityRoleEnum reqRole = SecurityRoleEnum.valueOf(user.getRole());

            // create list
            List<MeasureDTO> mList = new ArrayList<>();
            //make sure list isn't empty
            if (orgM != null && orgM.size() > 0){
                //convert ArrayList to HashSet to remove duplicates
                Set<OrganizationMeasureDTO> mSet = new HashSet<>(orgM);
                //loop through HashSet and find the Measure by Id, add to Measure List
                for (OrganizationMeasureDTO omd : mSet){
                    mList.add(measureDAO.findById(omd.getMeasureId()));
                }
            }

            return mList;

        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "findAllOrganizationMeasuresByOrg", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }
}
