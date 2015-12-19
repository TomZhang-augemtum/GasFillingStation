package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.MenuDao;
import com.gas.model.Menu;;
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<Menu> getList() {
        return menuDao.findAll();
    }

}
