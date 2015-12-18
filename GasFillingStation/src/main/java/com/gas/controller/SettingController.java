package com.gas.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.WechatConfig;
import com.gas.service.UserService;

@RestController
public class SettingController {
    @Autowired
    private UserService userService;

    @RequestMapping("/api/setting/list")
    public Map<String, String> index(HttpServletRequest request, Model model) {
        return WechatConfig.getWechatConfig().getSettings();
    }
}
