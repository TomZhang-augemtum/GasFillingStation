package com.gas.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.model.Chart;
import com.gas.model.Dataset;;
@Service
public class ChartService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Chart getCompany(Long companyid) {
        EntityManager em = entityManagerFactory.createEntityManager();
        String sql = "select sum(gasAmount) as gasTotalNum, sum(total) as moneyTotalNum, DATE_FORMAT(time,'%Y%m') months from costhistory where companyid = "
                + companyid.intValue() + " and time between '2012-01-01' and '2016-01-01' group by months";
        Query query = em.createNativeQuery(sql);
        Chart chart = new Chart();
        List<String> months = chart.getLabels();

        Dataset money = new Dataset();
        money.setFillColor("rgba(0,0,0,0)");
        money.setStrokeColor("rgba(220,220,220,1)");
        money.setPointColor("rgba(220,220,220,1)");
        money.setPointStrokeColor("#fff");
        List<Double> moneyData = money.getData();
        Dataset gas = new Dataset();
        gas.setFillColor("rgba(0,0,0,0)");
        gas.setStrokeColor("rgba(151,187,205,1)");
        gas.setPointColor("rgba(0,0,0,0)");
        gas.setPointStrokeColor("#fff");
        List<Double> gasData = gas.getData();
        List<Object> objecArraytList = query.getResultList();
        for (int i = 0; i < objecArraytList.size(); i++) {
            Object[] obj = (Object[]) objecArraytList.get(i);
            gasData.add((Double) obj[0]);
            moneyData.add((Double) obj[1]);
            months.add(obj[2].toString());
        }
        chart.getDatasets().add(money);
        chart.getDatasets().add(gas);
        em.close();

        return chart;
    }

}