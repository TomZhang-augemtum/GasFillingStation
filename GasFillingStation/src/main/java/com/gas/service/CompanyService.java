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

    public List<Company> getCompanyListByPagenation(int offset, int limit, String sort) {
        return companyDao.findCompanyByPagenation(offset, limit, sort);
    }

    public int getCompanyCount() {
        return companyDao.companyCount();
    }

    public Company save(Company company) {
        return companyDao.save(company);
    }
}
