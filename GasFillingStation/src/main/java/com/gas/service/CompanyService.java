package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

    public Page<Company> getCompanyListByPagenation(int page, int size, String sort) {
        String[] temp = sort.split(" ");
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.fromStringOrNull(temp[1]), temp[0]));
        Page<Company> companys = companyDao.findAll(pageable);
        for (Company company : companys) {
            company.setLeader(userDao.findNameById((long) company.getLeadingUser()));
        }
        return companys;
    }


    public Company save(Company company) {
        company.setPhone(userDao.findPhoneById((long) company.getLeadingUser()));
        return companyDao.save(company);
    }

    public void delete(Company company) {
        companyDao.delete(company);
    }
}
