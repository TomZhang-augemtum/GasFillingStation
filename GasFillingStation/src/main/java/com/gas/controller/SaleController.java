package com.gas.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Sale;
import com.gas.model.SalePageableAndSort;
import com.gas.service.SaleService;
import com.gas.utils.CSVUtil;
import com.gas.utils.DateUtil;

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

    @RequestMapping("/api/sale/company/csv")
    public void companycsv(HttpServletRequest request, HttpServletResponse response, String beginDate, String endDate)
            throws IOException {
        Date begin = DateUtil.parseFromString(beginDate);
        Date end = DateUtil.parseFromString(endDate);

        List<Sale> sales = saleService.getCompanyAll(begin, end).getContent();
        List<String> dataList = new ArrayList<String>();
        dataList.add("起始时间," + DateUtil.parseToLocal(begin) + ",截止时间," + DateUtil.parseToLocal(end));
        dataList.add("子站名,位置,总销气量,总营业额");
        for (Sale sale : sales) {
            String tmp = sale.getName() + "," + sale.getLocation() + "," + sale.getGasTotalNum() + ","
                    + sale.getMoneyTotalNum();
            dataList.add(tmp);
        }

        CSVUtil.exportCsv(new File("tmp.csv"), dataList);

        response.setContentType(request.getServletContext().getMimeType("csv"));
        response.setHeader("Content-Disposition", "attachment;filename=report.csv");
        InputStream in = new FileInputStream(new File("tmp.csv"));
        OutputStream out = response.getOutputStream();
        // 写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }

        in.close();
        out.close();
    }

    @RequestMapping("/api/sale/employee/csv")
    public void employeecsv(HttpServletRequest request, HttpServletResponse response, Long companyid,
 String beginDate,
            String endDate) throws IOException {

        Date begin = DateUtil.parseFromString(beginDate);
        Date end = DateUtil.parseFromString(endDate);

        List<Sale> sales = saleService.getEmployeeAll(companyid, begin, end).getContent();
        List<String> dataList = new ArrayList<String>();
        dataList.add("起始时间," + DateUtil.parseToLocal(begin) + ",截止时间," + DateUtil.parseToLocal(end));
        dataList.add("子站名,位置,总销气量,总营业额");
        for (Sale sale : sales) {
            String tmp = sale.getName() + "," + sale.getLocation() + "," + sale.getGasTotalNum() + ","
                    + sale.getMoneyTotalNum();
            dataList.add(tmp);
        }

        CSVUtil.exportCsv(new File("tmp.csv"), dataList);

        response.setContentType(request.getServletContext().getMimeType("csv"));
        response.setHeader("Content-Disposition", "attachment;filename=report.csv");
        InputStream in = new FileInputStream(new File("tmp.csv"));
        OutputStream out = response.getOutputStream();
        // 写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }

        in.close();
        out.close();
    }
}
