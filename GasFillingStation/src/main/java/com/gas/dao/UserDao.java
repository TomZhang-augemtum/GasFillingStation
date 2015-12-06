package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
    User findByName(String name);
    List<User> findAll();
}
