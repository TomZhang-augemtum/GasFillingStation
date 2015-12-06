package com.gas.service;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.UserDao;
import com.gas.model.User;
import com.gas.utils.StringUtil;;
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

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

    public List<User> getUserList() {
        return userDao.findAll();
    }
}
