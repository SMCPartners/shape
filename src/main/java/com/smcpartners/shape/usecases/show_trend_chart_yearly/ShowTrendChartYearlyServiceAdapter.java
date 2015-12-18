//package com.smcpartners.shape.usecases.show_trend_chart_yearly;
//
///**
// * Created by bryanhokanson on 12/18/15.
// */
//
//import com.smcpartners.shape.shared.dto.shape.response.TrendChartYearlyDTO;
//import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
//
//
//import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
//import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
//import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
//import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
//import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
//import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
//import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
//import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
//import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
//import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
//import com.smcpartners.shape.shared.dto.shape.response.TrendChartDTO;
//import com.smcpartners.shape.shared.usecasecommon.UseCaseException;
//
//
//import javax.ejb.EJB;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.PathParam;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * Created by bryanhokanson on 12/14/15.
// */
//@RequestScoped
//public class ShowTrendChartYearlyServiceAdapter implements ShowTrendChartYearlyService {
//
//    @Inject
//    private Logger log;
//
//    @EJB
//    private OrganizationMeasureDAO organizationMeasureDAO;
//
//    @EJB
//    private UserDAO userDAO;
//
//    @EJB
//    private MeasureDAO mDAO;
//
//    @EJB
//    private OrganizationDAO oDAO;
//
//    @EJB
//    private OrganizationMeasureDAO orgMDAO;
//
//    @Inject
//    private RequestScopedUserId requestScopedUserId;
//
//    public ShowTrendChartYearlyServiceAdapter() {
//    }
//
//    @Override
//    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
//    public List<TrendChartYearlyDTO> showTrendChartYearly(@PathParam("measureId") int measureId,
//                                                          @PathParam("year") int year) throws UseCaseException {
//
//        try {
//
//        } catch (Exception e) {
//
//        }
//
//    }
