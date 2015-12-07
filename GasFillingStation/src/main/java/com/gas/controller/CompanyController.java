package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
