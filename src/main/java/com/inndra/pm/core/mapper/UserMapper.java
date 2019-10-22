package com.inndra.pm.core.mapper;

import com.inndra.pm.core.domain.model.User;

import java.util.List;

/**
 * Created by edwin.rubio on 29/05/2014.
 */
public interface UserMapper {

    public void insertUser(User user);

    public User getUserById(Integer id);

    public List<User> getAllUsers();

    public void updateUser(User user);

    public void deleteUser(Integer id);

    List<User> getRecordByUserId(String id);

    List<User> getRecordByUserName(String description);
}
