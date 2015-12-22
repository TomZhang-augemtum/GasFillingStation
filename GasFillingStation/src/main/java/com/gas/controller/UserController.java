package com.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
    public Page<User> index(HttpServletRequest request, Model model, int page, int size, String order,
            String orderColumn) {
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.fromString(order), orderColumn));
        return userService.getUserList(pageable);
    }

    @RequestMapping("/api/employee/list/Pagenation")
    public Page<User> employeeListByPagenation(HttpServletRequest request, Model model, int limit, int offset,
            String sort) {
        return userService.getEmployeeListByPagenation(offset, limit, sort);
    }

    @RequestMapping("/api/customer/list/Pagenation")
    public Page<User> customerListByPagenation(HttpServletRequest request, Model model, int limit, int offset,
            String sort) {
        return userService.getCustomerListByPagenation(offset, limit, sort);
    }

    @RequestMapping(value = "/api/employee/save", method = RequestMethod.POST)
    public void saveEmployee(HttpServletRequest request, User user) {
        Role role = new Role();
        role.setId(2L);
        user.setRole(role);
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

    @RequestMapping("/api/user/current/info")
    public User currentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    @RequestMapping("/api/user/change/phone")
    public boolean changeUserPhone(HttpServletRequest request, Long userid, String phone) {
        userService.changeUserPhone(userid, phone);
        return true;
    }
}
