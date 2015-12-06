package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.User;
import com.gas.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/api/user/list")
    public List<User> index(HttpServletRequest request, Model model) {
        return userService.getUserList();
    }
}
