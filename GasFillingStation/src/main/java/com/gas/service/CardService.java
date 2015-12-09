package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gas.dao.CardDao;
import com.gas.dao.UserDao;
import com.gas.model.Card;;
@Service
public class CardService {

    @Autowired
    private CardDao cardDao;
    @Autowired
    private UserDao userDao;

    public List<Card> getList() {
        List<Card> cards = cardDao.findAll();
        for (Card card : cards) {
            card.setUser(userDao.findByCardid(card.getId()));
        }
        return cards;
    }

    public Card save(Card card) {
        return cardDao.save(card);
    }

    public void delete(Card card) {
        cardDao.delete(card);
    }

    public Page<Card> getListByPagenationAndOrder(Pageable page) {
        Page<Card> cards = cardDao.findAll(page);
        for (Card card : cards.getContent()) {
            card.setUser(userDao.findByCardid(card.getId()));
        }
        return cards;
//        return cards.getContent();
    }
}
