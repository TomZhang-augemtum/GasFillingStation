package com.gas.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.SettingVO;
import com.gas.model.WechatConfig;
import com.gas.service.SettingService;

@RestController
public class SettingController {
    @Autowired
    private SettingService settingService;
    @RequestMapping("/api/setting/list")
    public Map<String, String> index(HttpServletRequest request) {
        return WechatConfig.getWechatConfig().getSettings();
    }

    @RequestMapping(value = "/api/setting/update", method = RequestMethod.POST)
    public boolean update(HttpServletRequest request, SettingVO settingvo) {
        return settingService.update(settingvo);
    }
}
