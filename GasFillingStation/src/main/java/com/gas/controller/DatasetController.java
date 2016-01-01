package com.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.utils.DatasetUtil;

@RestController
public class DatasetController {

    @RequestMapping("/api/dataset/backup")
    public boolean backup(HttpServletRequest request) throws Exception {
        String binPath = "C:/Users/Tom.Zhang/mysql-5.6.24-winx64/bin";
        String userName = "root";
        String pwd = "root";
        DatasetUtil bak = new DatasetUtil(binPath, userName, pwd);
        bak.backup("gas.sql", "gas");
        return true;
    }

    @RequestMapping("/api/dataset/restore")
    public boolean restore(HttpServletRequest request) throws Exception {
        String binPath = "C:/Users/Tom.Zhang/mysql-5.6.24-winx64/bin";
        String userName = "root";
        String pwd = "root";
        DatasetUtil bak = new DatasetUtil(binPath, userName, pwd);
        bak.restore("gas.sql", "gas");
        return true;
    }
}
