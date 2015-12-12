package com.gas.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gas.model.Card;

public interface CardDao extends PagingAndSortingRepository<Card, String> {
    List<Card> findAll();
}
