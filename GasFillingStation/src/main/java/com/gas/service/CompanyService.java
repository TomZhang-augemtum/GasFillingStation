package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.CompanyDao;
import com.gas.model.Company;;
@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public List<Company> getCompanyList() {
        return companyDao.findAll();
    }
}
