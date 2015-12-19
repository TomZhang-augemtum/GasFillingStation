package com.gas.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gas.service.MenuService;
import com.gas.service.RoleService;
import com.gas.service.UserService;

@RestController
public class PermissionController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/api/permission/list")
    public Map<String, Object> index(HttpServletRequest request) {
        Map<String, Object> lists = new HashMap<String, Object>();
        lists.put("menus", menuService.getList());
        lists.put("roles", roleService.getList());
        return lists;
    }

    @RequestMapping(value = "/api/permission/update", method = RequestMethod.POST)
    public boolean update(HttpServletRequest request, Long roleid, Long menuid) {
        roleService.updateMenu(roleid, menuid);
        return true;
    }

    @RequestMapping(value = "/api/permission/userrole/update", method = RequestMethod.POST)
    public boolean updateUserRole(HttpServletRequest request, Long roleid, Long userid) {
        userService.updateRole(userid, roleid);
        return true;
    }
}
