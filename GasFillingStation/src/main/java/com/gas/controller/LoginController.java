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
import com.gas.service.UserService;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

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
        request.getSession().setAttribute("user", loginuser);
        return "redirect:" + loginuser.getRole().getMenus().get(0).getUrl();
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "login";
    }

    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public String reset(HttpServletRequest request, String username) {
        User user = userService.findByName(username);
        Map<String, String> settings = WechatConfig.getWechatConfig().getSettings();
        WxCpMessage message = WxCpMessage.TEXT()
                .agentId(WechatConfig.getWechatConfig().getWxCpInMemoryConfigStorage().getAgentId()) // 企业号应用ID
                .toUser(user.getNumber())
                .content("<a href='" + settings.get("domain") + "/wx/reset/password'>点此重置密码</a>")
                .build();

        try {
            WechatConfig.getWechatConfig().getWxCpService().messageSend(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "login";
    }
}
