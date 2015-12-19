package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.Menu;

public interface MenuDao extends PagingAndSortingRepository<Menu, Long> {
    List<Menu> findAll();
}
