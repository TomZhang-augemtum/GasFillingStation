package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Sale;
import com.gas.model.SalePageableAndSort;
import com.gas.service.SaleService;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    @RequestMapping("/api/sale/company")
    public List<Sale> list(HttpServletRequest request, Model model, SalePageableAndSort pageAndSort) {
        System.out.println(pageAndSort);
        return saleService.getCompany(pageAndSort);
    }
}
