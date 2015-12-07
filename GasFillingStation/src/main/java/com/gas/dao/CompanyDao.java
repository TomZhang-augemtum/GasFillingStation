package com.gas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.gas.model.Company;

public interface CompanyDao extends PagingAndSortingRepository<Company, Long> {
    List<Company> findAll();

    @Query(value = "select * from Company order by :sort limit :offset,:size ", nativeQuery = true)
    List<Company> findCompanyByPagenation(@Param("offset") int offset, @Param("size") int size,
            @Param("sort") String sort);

    @Query(value = "select count(1) from Company", nativeQuery = true)
    int companyCount();

    Company save(Company company);
}
