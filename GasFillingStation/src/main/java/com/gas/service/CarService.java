package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.CarDao;
import com.gas.model.Car;;
@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    public List<Car> getList() {
        return carDao.findAll();
    }

    public void save(Car car) {
        carDao.save(car);
    }

    public void delete(Car car) {
        carDao.delete(car);
    }

}
