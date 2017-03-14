package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.User;
import com.edu.nju.wel.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zs on 2017/3/2.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    public List<User> find() {
        return DAOManager.userDao.find();
    }
}
