package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Chart;
import com.gas.model.Company;
import com.gas.service.ChartService;
import com.gas.service.CompanyService;

@RestController
public class ChartController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ChartService chartService;

    @RequestMapping("/api/chart/company/list")
    public List<Company> list(HttpServletRequest request) {
        return companyService.getCompanyList();
    }

    @RequestMapping("/api/chart/data")
    public Chart data(HttpServletRequest request, Long companyid) {
        return chartService.getCompany(companyid);
    }
}
