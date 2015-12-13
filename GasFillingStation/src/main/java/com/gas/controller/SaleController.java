package com.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Sale> company(HttpServletRequest request, Model model, SalePageableAndSort pageAndSort) {
        return saleService.getCompany(pageAndSort);
    }

    @RequestMapping("/api/sale/employee")
    public Page<Sale> employee(HttpServletRequest request, Model model, SalePageableAndSort pageAndSort,
            Long companyid) {
        return saleService.getEmployee(pageAndSort, companyid);
    }
}
