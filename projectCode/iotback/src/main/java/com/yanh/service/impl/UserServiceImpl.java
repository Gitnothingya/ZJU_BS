package com.yanh.service.impl;

import com.yanh.mapper.UserMapper;
import com.yanh.pojo.User;
import com.yanh.service.UserService;
import com.yanh.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
//        User user = userMapper.findByUsername(username);
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        userMapper.addUser(username, password);
    }

    @Override
    public void updateBasicInfo(User user) {
        userMapper.update(user);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(newPwd, id);
    }
}
