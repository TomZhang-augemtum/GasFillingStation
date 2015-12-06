package com.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gas.model.User;
import com.gas.service.UserService;

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
            // logger.debug(e.getMessage());
            // redirect.addFlashAttribute("err_code", e.getMessage());
            // redirect.addFlashAttribute("user", user);
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }
        request.getSession().setAttribute("user", loginuser);
        model.addAttribute("users", userService.getUserList());
        return "dashboard";
    }

    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "login";
    }
}
