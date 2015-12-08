package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Company;
import com.gas.service.CompanyService;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/api/company/list")
    public List<Company> index(HttpServletRequest request, Model model) {
        return companyService.getCompanyList();
    }

    @RequestMapping("/api/company/list/Pagenation")
    public List<Company> companyListByPagenation(HttpServletRequest request, Model model, int limit, int offset,
            String sort) {
        return companyService.getCompanyListByPagenation(offset, limit, sort);
    }

    @RequestMapping("/api/company/list/count")
    public int companyListCount(HttpServletRequest request, Model model) {
        return companyService.getCompanyCount();
    }

    @RequestMapping(value = "/api/company/save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, Company company) {
        companyService.save(company);
    }

    @RequestMapping("/api/company/delete")
    public void delete(HttpServletRequest request, Company company) {
        companyService.delete(company);
    }
}
