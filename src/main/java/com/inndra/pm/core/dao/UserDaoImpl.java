package com.inndra.pm.core.dao;

import com.inndra.pm.core.domain.model.User;
import com.inndra.pm.core.mapper.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Resource
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public List<User> getRecordByUserId(String id) {
        return userMapper.getRecordByUserId("%" + id + "%");
    }

    @Override
    public List<User> getRecordByUserName(String description) {
        return userMapper.getRecordByUserName("%" + description + "%");
    }


    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}
