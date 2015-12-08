package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gas.dao.CardDao;
import com.gas.model.Card;;
@Service
public class CardService {

    @Autowired
    private CardDao cardDao;

    public List<Card> getList() {
        return cardDao.findAll();
    }

    public void save(Card card) {
        cardDao.save(card);
    }

    public void delete(Card card) {
        cardDao.delete(card);
    }

    public Page<Card> getListByPagenationAndOrder(Pageable page) {
        return cardDao.findAll(page);
//        return cards.getContent();
    }
}
