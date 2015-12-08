package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.CarType;
import com.gas.service.CartypeService;

@RestController
public class CartypeController {
    @Autowired
    private CartypeService cartypeService;

    @RequestMapping("/api/cartype/list")
    public List<CarType> list(HttpServletRequest request, Model model) {
        return cartypeService.getList();
    }

    @RequestMapping(value = "/api/cartype/save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, CarType carType) {
        cartypeService.save(carType);
    }

    @RequestMapping("/api/cartype/delete")
    public void delete(HttpServletRequest request, CarType carType) {
        cartypeService.delete(carType);
    }
}
