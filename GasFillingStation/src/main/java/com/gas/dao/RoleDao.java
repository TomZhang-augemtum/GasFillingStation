package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {
    List<Role> findAll();
}
