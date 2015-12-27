package com.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("/permission")
    public String report(HttpServletRequest request, Model model) {
        return "permission";
    }

    @RequestMapping("/nopermission")
    public String noperomission(HttpServletRequest request, Model model) {
        return "nopermission";
    }

    @RequestMapping("/setting")
    public String setting(HttpServletRequest request, Model model) {
        return "setting";
    }

    @RequestMapping("/wx/business")
    public String wxBusiness(HttpServletRequest request, Model model) {
        return "wx/business";
    }

    @RequestMapping("/wx/customer/card")
    public String wxCard(HttpServletRequest request, Model model) {
        return "wx/card";
    }

    @RequestMapping("/wx/customer/info")
    public String wxInfo(HttpServletRequest request, Model model) {
        return "wx/info";
    }

    @RequestMapping(value = "/wx/reset/password", method = RequestMethod.GET)
    public String resetPass(HttpServletRequest request) {
        return "wx/reset";
    }
}
