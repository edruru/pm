package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.User;

import java.util.List;

public interface UserDao {

    void insertUser(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Integer id);

    List<User> getRecordByUserId(String id);

    List<User> getRecordByUserName(String description);

}
