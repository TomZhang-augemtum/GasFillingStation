package com.gas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.RoleDao;
import com.gas.model.Menu;
import com.gas.model.Role;;
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> getList() {
        return roleDao.findAll();
    }

    public void updateMenu(Long roleid, Long menuid) {
        Role role = roleDao.findOne(roleid);
        for(Menu m : role.getMenus()) {
            if (m.getId() == menuid) {
                role.getMenus().remove(m);
                roleDao.save(role);
                return;
            }
        }
        Menu menu = new Menu();
        menu.setId(menuid);
        role.getMenus().add(menu);
        roleDao.save(role);
    }

}
