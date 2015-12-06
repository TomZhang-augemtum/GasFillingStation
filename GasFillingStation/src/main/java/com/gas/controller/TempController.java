package com.gas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gas.service.UserService;

@Controller
public class TempController {
    @Autowired
    private UserService userService;
}
