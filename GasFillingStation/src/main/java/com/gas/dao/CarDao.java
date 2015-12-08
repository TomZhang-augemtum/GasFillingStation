package com.gas.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.Car;

public interface CarDao extends PagingAndSortingRepository<Car, Long> {
    List<Car> findAll();

    Page<Car> findAll(Pageable page);
}
