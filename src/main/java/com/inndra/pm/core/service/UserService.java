package com.inndra.pm.core.service;

import com.inndra.pm.core.domain.model.User;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public interface UserService {

    void insertUser(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(Integer id);

    List<User> getRecordByUserId(String id);

    List<User> getRecordByUserName(String description);
}
