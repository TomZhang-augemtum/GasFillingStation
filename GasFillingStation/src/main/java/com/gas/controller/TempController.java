package com.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gas.model.User;
import com.gas.service.UserService;

@Controller
public class TempController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        User user = userService.login("tom");
        request.getSession().setAttribute("user", user);
        return "index";
    }
}
