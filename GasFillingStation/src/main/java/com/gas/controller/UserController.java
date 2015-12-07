package com.gas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Role;
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

    @RequestMapping("/api/employee/list/Pagenation")
    public List<User> employeeListByPagenation(HttpServletRequest request, Model model, int limit, int offset,
            String sort) {
        System.out.println(sort);
        return userService.getEmployeeListByPagenation(offset, limit, sort);
    }

    @RequestMapping("/api/employee/list/count")
    public int employeeListCount(HttpServletRequest request, Model model) {
        return userService.getEmployeeCount();
    }

    @RequestMapping("/api/customer/list/Pagenation")
    public List<User> customerListByPagenation(HttpServletRequest request, Model model, int limit, int offset,
            String sort) {
        System.out.println(sort);
        return userService.getCustomerListByPagenation(offset, limit, sort);
    }

    @RequestMapping("/api/customer/list/count")
    public int customerListCount(HttpServletRequest request, Model model) {
        return userService.getCustomerCount();
    }

    @RequestMapping(value = "/api/employee/save", method = RequestMethod.POST)
    public void saveEmployee(HttpServletRequest request, User user) {
        Role role = new Role();
        role.setId(2L);
        user.setRole(role);
        System.out.println(user.getName());
        userService.saveUser(user);
    }

    @RequestMapping("/api/customer/save")
    public void saveCustomer(HttpServletRequest request, User user) {
        userService.saveUser(user);
    }

    @RequestMapping("/api/user/delete")
    public void deleteUser(HttpServletRequest request, User user) {
        userService.deleteUser(user);
    }
}
