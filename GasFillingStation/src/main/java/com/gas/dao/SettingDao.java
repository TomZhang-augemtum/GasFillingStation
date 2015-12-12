package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.Setting;

public interface SettingDao extends PagingAndSortingRepository<Setting, Long> {
    List<Setting> findAll();
}
