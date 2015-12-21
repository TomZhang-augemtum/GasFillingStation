package com.gas.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gas.dao.RoleDao;
import com.gas.dao.UserDao;
import com.gas.model.Role;
import com.gas.model.User;
import com.gas.utils.StringUtil;;
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public User login(User user) {
        if (StringUtil.isEmpty(user.getName())) {
            throw new ServiceException("用户名不能为空");
        }

        if (StringUtil.isEmpty(user.getPassword())) {
            throw new ServiceException("密码不能为空");
        }
        User dataAdmin = userDao.findByName(user.getName());

        if (null == dataAdmin) {
            throw new ServiceException("用户名不存在");
        }

        if (!user.getPassword().equals(user.getPassword())) {
            throw new ServiceException("密码输入错误");
        }
        return dataAdmin;
    }

    public Page<User> getUserList(Pageable page) {
        return userDao.findAll(page);
    }


    public Page<User> getEmployeeListByPagenation(int page, int size, String sort) {
        String[] tmp = sort.split(" ");
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.fromString(tmp[1]), tmp[0]));
        Role role = new Role();
        role.setId(3L);
        return userDao.findAllByRoleNot(pageable, role);
    }

    public Page<User> getCustomerListByPagenation(int page, int size, String sort) {
        String[] tmp = sort.split(" ");
        Pageable pageable = new PageRequest(page, size, new Sort(Direction.fromString(tmp[1]), tmp[0]));
        return userDao.findAll(pageable);
    }

    public User saveUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public User findOne(Long id) {
        return userDao.findOne(id);
    }

    public User wxLogin(String number) {
        return userDao.findOneByNumber(number);
    }

    public void updateRole(Long userid, Long roleid) {
        User user = userDao.findOne(userid);
        user.setRole(roleDao.findOne(roleid));
        userDao.save(user);
    }

    public void changeUserPhone(Long userid, String phone) {
        User user = userDao.findOne(userid);
        user.setPhone(phone);
        userDao.save(user);

    }
}
