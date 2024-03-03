package com.yanh.service;

import com.yanh.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUsername(String username);

    // 注册服务
    void register(String username, String password);

    //更新基本信息
    void updateBasicInfo(User user);

    //更新密码
    void updatePwd(String newPwd);
}
