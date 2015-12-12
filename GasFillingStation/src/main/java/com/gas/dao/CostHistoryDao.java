package com.gas.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.CostHistory;
import com.gas.model.Sale;

public interface CostHistoryDao extends PagingAndSortingRepository<CostHistory, Long> {
    List<CostHistory> findAll();

    List<Sale> findAll(Specification<CostHistory> specification);

}
