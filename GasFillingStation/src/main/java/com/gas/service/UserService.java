package com.gas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.UserDao;
import com.gas.model.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(String name) {
        User dataAdmin = userDao.findByName(name);
        return dataAdmin;
    }

}
