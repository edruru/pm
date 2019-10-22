package com.inndra.pm.core.service;

import com.inndra.pm.core.dao.UserDao;
import com.inndra.pm.core.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void insertUser(User user) {
        //TODO: Perform validation & exception handling
        userDao.insertUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        //TODO: Perform validation & exception handling
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        //TODO: Perform validation & exception handling
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        //TODO: Perform validation & exception handling
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        //TODO: Perform validation & exception handling
        userDao.deleteUser(id);
    }

    @Override
    public List<User> getRecordByUserId(String id) {
        return userDao.getRecordByUserId(id);
    }

    @Override
    public List<User> getRecordByUserName(String description) {
        return userDao.getRecordByUserName(description);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
