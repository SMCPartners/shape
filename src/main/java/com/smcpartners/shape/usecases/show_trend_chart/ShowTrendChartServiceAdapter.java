//package com.smcpartners.shape.usecases.show_trend_chart;
//
//import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
//import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
//import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
//import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
//import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
//import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
//import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
//import com.smcpartners.shape.shared.dto.shape.response.ListViewDTO;
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
//public class ShowTrendChartServiceAdapter implements ShowTrendChartService {
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
//    private OrganizationMeasureDAO orgMDAO;
//
//    @Inject
//    private RequestScopedUserId requestScopedUserId;
//
//    public ShowTrendChartServiceAdapter() {
//    }
//
//    @Override
//    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
//    public List<TrendChartDTO> showTrendChart(@PathParam("orgId") int orgId,
//                                              @PathParam("measureId") int measureId,
//                                              @PathParam("year") int year) throws UseCaseException {
//        try {
//            //set variables
//            String quarter = "Quarter";
//            String aggregate = "Aggregate";
//            String org = "Org";
//            String q = "Q";
//
//            //get the organization measures
//            List<OrganizationMeasureDTO> orgMList = orgMDAO.findAllOrganizationMeasureByOrgId(orgId);
//        } catch Exception(e) {
//            log.logp(Level.SEVERE, this.getClass().getName(), "showListView", e.getMessage(), e);
//            throw new UseCaseException(e.getMessage());
//        }
//
//    }
//
//    public int trendMath(List<OrganizationMeasureDTO> orgMList, int quarter){
//
//    }
//}
