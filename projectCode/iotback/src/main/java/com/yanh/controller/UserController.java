package com.yanh.controller;

import com.yanh.pojo.Result;
import com.yanh.pojo.User;
import com.yanh.service.TokenService;
import com.yanh.service.UserService;
import com.yanh.utils.JwtUtil;
import com.yanh.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    //根据用户名和密码注册用户，用户名不允许重复
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,20}$") String username, @Pattern(regexp = "^\\S{5,32}$")String password) {
        // 查询是否存在重复用户名
        User u = userService.findByUsername(username);
        if(u == null) {
            //注册服务
            userService.register(username, password);
            return Result.success();
        } else {
            //提示用户
            return Result.error("该用户名已被占用，请重新输入");
        }
    }

    @PostMapping("/login")
    public Result login(String username, String password) {
        //根据输入需要有不同地提示信息
        //先查询用户名是否存在
        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.error("用户名错误");
        }

        if(password.equals(user.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);

            // 将token存入数据库,用于主动失效
            tokenService.addToken(token);

            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/info")
    public Result<User> info() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);

        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.updateBasicInfo(user);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token ) {
        // 获取参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        // 校验参数
        // 都需有值
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        // 检查原密码
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        if(!user.getPassword().equals(oldPwd)) {
            return Result.error("原密码错误");
        }

        // 检查两个新密码
        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }

        // 校验新密码格式
        if (newPwd.length() < 5 || newPwd.length() > 32) {
            return Result.error("新密码需要5-32位非空字符");
        }

        //校验结束,进行更新
        userService.updatePwd(newPwd);
        //删除旧的token
        tokenService.deleteToken(token);
        return Result.success();
    }

}
