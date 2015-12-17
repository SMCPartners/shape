package com.smcpartners.shape.usecases.show_trend_chart;

import com.smcpartners.shape.crosscutting.security.RequestScopedUserId;
import com.smcpartners.shape.crosscutting.security.annotations.SecureRequireActiveLogAvtivity;
import com.smcpartners.shape.frameworks.data.dao.shape.MeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.OrganizationMeasureDAO;
import com.smcpartners.shape.frameworks.data.dao.shape.UserDAO;
import com.smcpartners.shape.shared.constants.SecurityRoleEnum;
import com.smcpartners.shape.shared.dto.shape.MeasureDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationDTO;
import com.smcpartners.shape.shared.dto.shape.OrganizationMeasureDTO;
import com.smcpartners.shape.shared.dto.shape.response.TrendChartDTO;
import com.smcpartners.shape.shared.usecasecommon.UseCaseException;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by bryanhokanson on 12/14/15.
 */
@RequestScoped
public class ShowTrendChartServiceAdapter implements ShowTrendChartService {

    @Inject
    private Logger log;

    @EJB
    private OrganizationMeasureDAO organizationMeasureDAO;

    @EJB
    private UserDAO userDAO;

    @EJB
    private MeasureDAO mDAO;

    @EJB
    private OrganizationDAO oDAO;

    @EJB
    private OrganizationMeasureDAO orgMDAO;

    @Inject
    private RequestScopedUserId requestScopedUserId;

    public ShowTrendChartServiceAdapter() {
    }

    @Override
    @SecureRequireActiveLogAvtivity({SecurityRoleEnum.ADMIN, SecurityRoleEnum.ORG_ADMIN, SecurityRoleEnum.REGISTERED})
    public List<TrendChartDTO> showTrendChart(@PathParam("measureId") int measureId,
                                              @PathParam("year") int year) throws UseCaseException {
        try {
            //set variables
            String quarter = "Quarter";
            String aggregate = "Aggregate";


            //make return list
            List<TrendChartDTO> retList = new ArrayList<>();

            //create the new array lists that will hold the quarter/
            MeasureDTO mdto = mDAO.findById(measureId);
            //get the organization measures
            List<OrganizationMeasureDTO> orgMList = orgMDAO.findOrgMeasureByMeasureIdAndYear(measureId, year);

             class Sort implements Comparator<OrganizationMeasureDTO> {
                        @Override
                        public int compare(OrganizationMeasureDTO org1, OrganizationMeasureDTO org2) {
                            return org1.getOrganizationId() - org2.getOrganizationId();
                        }
                    }

            Collections.sort(orgMList, new Sort());

            TrendChartDTO tcDTO = new TrendChartDTO();
            tcDTO.setName(mdto.getName());
            tcDTO.setDescription(mdto.getDescription());
            tcDTO.setNumeratorDescription(mdto.getNumeratorDescription());
            tcDTO.setDenominatorDescription(mdto.getDenominatorDescription());

            List<List<Object>> doubleList = new ArrayList<>();
            List<Object> headerList = new ArrayList<>();

            OrganizationDTO organizationDTO = oDAO.findById(orgMList.get(0).getOrganizationId());

            headerList.add(quarter);
            headerList.add(aggregate);
            headerList.add(organizationDTO.getName());


            List<Object> q1List = new ArrayList<>();
            List<Object> q2List = new ArrayList<>();
            List<Object> q3List = new ArrayList<>();
            List<Object> q4List = new ArrayList<>();

            q1List.add("Q1");
            q2List.add("Q2");
            q3List.add("Q3");
            q4List.add("Q4");

            double[] finalList = aggregateTotal(orgMList);

            q1List.add(finalList[0]);
            q2List.add(finalList[1]);
            q3List.add(finalList[2]);
            q4List.add(finalList[3]);

            int orgToCheck = orgMList.get(0).getOrganizationId();

            for (OrganizationMeasureDTO om : orgMList) {
                int quarterNum = om.getReportPeriodQuarter();
                OrganizationDTO orgDTO = oDAO.findById(om.getOrganizationId());

                if (orgToCheck != om.getOrganizationId()) headerList.add(orgDTO.getName());

                    switch (quarterNum) {

                        case 1:
                            q1List.add(divisionOfNumDen(om));
                            break;

                        case 2:
                            q2List.add(divisionOfNumDen(om));
                            break;

                        case 3:
                            q3List.add(divisionOfNumDen(om));
                            break;

                        case 4:
                            q4List.add(divisionOfNumDen(om));
                            break;

                    }



                orgToCheck = om.getOrganizationId();

            }

            doubleList.add(q1List);
            doubleList.add(q2List);
            doubleList.add(q3List);
            doubleList.add(q4List);

            tcDTO.setTrendChart(doubleList);

            doubleList.add(0, headerList);

            retList.add(tcDTO);

            return retList;



        } catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "showListView", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }

    }

    public double divisionOfNumDen (OrganizationMeasureDTO orgMDTO) {
        int numValue = orgMDTO.getNumeratorValue();
        int denValue = orgMDTO.getDenominatorValue();
        double decimal = (double)numValue/denValue;
        DecimalFormat df = new DecimalFormat("#.000");
        decimal = Double.valueOf(df.format(decimal));
        String noZero = String.valueOf(decimal);
        noZero = noZero.substring(1 , noZero.length()- 1);
        if (noZero.equals(".")){
            noZero = "1.00";
        }
        decimal = Double.parseDouble(noZero);

        return decimal;
    }

    public double[] aggregateTotal (List<OrganizationMeasureDTO> orgMList) {
        try {
            double sumOfQ1Num = 0.0;
            double sumOfQ2Num = 0.0;
            double sumOfQ3Num = 0.0;
            double sumOfQ4Num = 0.0;
            double sumOfQ1Den = 0.0;
            double sumOfQ2Den = 0.0;
            double sumOfQ3Den = 0.0;
            double sumOfQ4Den = 0.0;

            for (OrganizationMeasureDTO om : orgMList){
                switch(om.getReportPeriodQuarter()) {

                    case 1 : sumOfQ1Num += om.getNumeratorValue();
                             sumOfQ1Den += om.getDenominatorValue();
                             break;

                    case 2 : sumOfQ2Num += om.getNumeratorValue();
                             sumOfQ2Den += om.getDenominatorValue();
                             break;

                    case 3 : sumOfQ3Num += om.getNumeratorValue();
                             sumOfQ3Den += om.getDenominatorValue();
                             break;

                    case 4 : sumOfQ4Num += om.getNumeratorValue();
                             sumOfQ4Den += om.getDenominatorValue();
                             break;
                }
            }

            double[] totals = new double[4];
            totals[0] = sumOfQ1Num / sumOfQ1Den;
            totals[1] = sumOfQ2Num / sumOfQ2Den;
            totals[2] = sumOfQ3Num / sumOfQ3Den;
            totals[3] = sumOfQ4Num / sumOfQ4Den;

            return totals;

        }catch (Exception e) {
            log.logp(Level.SEVERE, this.getClass().getName(), "aggregateTotal", e.getMessage(), e);
            throw new UseCaseException(e.getMessage());
        }
    }

}

