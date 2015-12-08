package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.CarType;

public interface CartypeDao extends PagingAndSortingRepository<CarType, Long> {
    List<CarType> findAll();

}
