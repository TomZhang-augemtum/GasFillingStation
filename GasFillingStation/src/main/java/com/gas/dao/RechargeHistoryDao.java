package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.RechargeHistory;

public interface RechargeHistoryDao extends PagingAndSortingRepository<RechargeHistory, Long> {
    List<RechargeHistory> findAll();
}
