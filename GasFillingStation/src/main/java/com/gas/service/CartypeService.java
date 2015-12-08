package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.CartypeDao;
import com.gas.model.CarType;;
@Service
public class CartypeService {

    @Autowired
    private CartypeDao cartypeDao;

    public List<CarType> getList() {
        return cartypeDao.findAll();
    }

    public void save(CarType carType) {
        cartypeDao.save(carType);
    }

    public void delete(CarType carType) {
        cartypeDao.delete(carType);
    }

}
