package com.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempController {
    @RequestMapping("/dashboard")
    public String index(HttpServletRequest request, Model model) {
        return "dashboard";
    }

    @RequestMapping("/users")
    public String users(HttpServletRequest request, Model model) {
        return "users";
    }

    @RequestMapping("/substation")
    public String substation(HttpServletRequest request, Model model) {
        return "substation";
    }

    @RequestMapping("/business")
    public String business(HttpServletRequest request, Model model) {
        return "business";
    }

    @RequestMapping("/data")
    public String data(HttpServletRequest request, Model model) {
        return "data";
    }

    @RequestMapping("/report")
    public String report(HttpServletRequest request, Model model) {
        return "report";
    }

    @RequestMapping("/setting")
    public String setting(HttpServletRequest request, Model model) {
        return "setting";
    }
}
