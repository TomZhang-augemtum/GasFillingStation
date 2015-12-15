package com.gas.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gas.model.User;
import com.gas.model.WechatConfig;
import com.gas.service.SettingService;
import com.gas.service.UserService;

import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private SettingService settingService;
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        return "login";
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model, User user) {
        User loginuser = null;
        try {
            loginuser = userService.login(user);
        } catch (ServiceException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
        Map<String, String> settings = settingService.findAll();
        request.getSession().setAttribute("user", loginuser);
        request.getSession().setAttribute("settings", settings);
        WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
        config.setCorpId(settings.get("corpid")); // 设置微信企业号的appid
        config.setCorpSecret(settings.get("secret")); // 设置微信企业号的app corpSecret
        config.setAgentId(settings.get("agentid")); // 设置微信企业号应用ID
        config.setToken(settings.get("Token")); // 设置微信企业号应用的token
        config.setAesKey(settings.get("EncodingAESKey")); // 设置微信企业号应用的EncodingAESKey
        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);
        WechatConfig.setWxCpInMemoryConfigStorage(config);
        WechatConfig.setWxCpService(wxCpService);
        // model.addAttribute("users", userService.getUserList());
        return "redirect:" + loginuser.getRole().getMenus().get(0).getUrl();
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "login";
    }
}
