package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.CompanyDao;
import com.gas.dao.UserDao;
import com.gas.model.Company;;
@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UserDao userDao;

    public List<Company> getCompanyList() {
        return companyDao.findAll();
    }

    public List<Company> getCompanyListByPagenation(int offset, int limit, String sort) {
        List<Company> companys = companyDao.findCompanyByPagenation(offset, limit, sort);
        for (Company company : companys) {
            company.setLeader(userDao.findNameById((long) company.getLeadingUser()));
        }
        return companys;
    }

    public int getCompanyCount() {
        return companyDao.companyCount();
    }

    public Company save(Company company) {
        company.setPhone(userDao.findPhoneById((long) company.getLeadingUser()));
        return companyDao.save(company);
    }

    public void delete(Company company) {
        companyDao.delete(company);
    }
}
