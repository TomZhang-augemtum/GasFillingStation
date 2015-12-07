package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.Company;

public interface CompanyDao extends PagingAndSortingRepository<Company, Long> {
    List<Company> findAll();
}
