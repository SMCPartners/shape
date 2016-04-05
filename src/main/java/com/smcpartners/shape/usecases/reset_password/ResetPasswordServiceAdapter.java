package com.smcpartners.shape.usecases.reset_password;

import com.smcpartners.shape.crosscutting.email.MailDTO;
import com.smcpartners.shape.crosscutting.email.SendMailService;
import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.dto.common.BooleanValueDTO;
import com.smcpartners.shape.shared.dto.common.UsecaseRequest;
import com.smcpartners.shape.shared.dto.common.UsecaseResponse;
import com.smcpartners.shape.shared.dto.shape.UserDTO;
import com.smcpartners.shape.shared.dto.shape.request.PasswordUpdateRequestDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
import com.smcpartners.shape.shared.utils.UCHelpers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible:<br/>
 * 1. Adapter for framework services. Any user can reset their password
 * <p>
 * Created by jjdestef3 on 12/21/15.
 * <p>
 * Changes:<b/>
 */
@RequestScoped
public class ResetPasswordServiceAdapter implements ResetPasswordService, ResetPasswordUCAdapter {

    @Inject
    private Logger log;

    @EJB
    private UserDAO userDAO;

    @EJB
    private SendMailService sms;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    private ResetPasswordUC resetPasswordUC;


    public ResetPasswordServiceAdapter() {
    }

    @PostConstruct
    protected void postConstruct() {
        this.resetPasswordUC = new ResetPasswordUC(this);
    }

    @Override
    // @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.REGISTERED, SecurityRoleEnum.ORG_ADMIN})
    public BooleanValueDTO resetPassword(PasswordUpdateRequestDTO userReq) throws UseCaseException {
        try {
            // Request needs the organization id
            UsecaseRequest request = UCHelpers.createRequest(
                    UCHelpers.makeStringArray(ResetPasswordUCAdapter.USER_REQUEST), userReq);
            UsecaseResponse resp = this.resetPasswordUC.processRequest(request);
            return UCHelpers.processResponse(resp, BooleanValueDTO.class);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "resetPassword", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    /**
     * Rest the users password
     *
     * @param userId
     * @param newPassword
     * @throws Exception
     */
    @Override
    public void resetPassword(String userId, String newPassword) throws Exception {
        try {
            UserDTO uDTO = userDAO.findById(userId);
            userDAO.resetPasswordToggle(userId, true);
            MailDTO mail = new MailDTO();
            mail.setToEmail(uDTO.getEmail());
            mail.setSubject("Your password has been reset");
            mail.setMessage("Your password has been reset and changed to the temporary password: " + newPassword + "\n"
                    + "Please log in using your temporary password. You will be prompted to change this " +
                    "password after a successful login");
            sms.sendEmailMsg(mail);
            userDAO.forcePasswordChange(userId, newPassword);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "resetPassword", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

    /**
     * Get the user record for the given id
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public UserDTO getUserData(String userId) throws Exception {
        try {
            return userDAO.findById(userId);
        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "getUserData", e.getMessage(), e);
            throw new UseCaseException("Incorrect username");
        }
    }
}
