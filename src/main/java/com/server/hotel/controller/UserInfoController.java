package com.server.hotel.controller;

import com.server.hotel.common.Result;
import com.server.hotel.entry.UserInfo;
import com.server.hotel.service.impl.UserInfoServiceImpl;
import com.server.hotel.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    UserInfoServiceImpl userService;

    @PostMapping("login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, Object> request) {
        String phone = (String) request.get("phone");
        String password = (String) request.get("password");
        if (null == phone || null == password) {
            return Result.fail("手机号密码不能为空");
        }

        UserInfo user = userService.login(phone, password);
        if (null == user) {
            return Result.fail("手机号或密码错误");
        }


        String token = TokenUtils.getToken(user);
        Map<String, Object> userResult = new HashMap<>();
        userResult.put("id", user.getId());
        userResult.put("phone", user.getPhone());
        userResult.put("firstname", user.getFirstname());
        userResult.put("lastname", user.getLastname());
        userResult.put("email", user.getEmail());
        userResult.put("token", token);
        return Result.success("登录成功", userResult);

    }

    @PostMapping("register")
    public Result<Map<String, Object>> register(@RequestBody Map<String, Object> request) {
        UserInfo user = new UserInfo();
        try{
            user.setFirstname((String) request.get("firstname"));
            user.setLastname((String) request.get("lastname"));
            user.setPhone((String) request.get("phone"));
            user.setEmail((String) request.get("email"));
            user.setPassword(DigestUtils.md5DigestAsHex(((String) request.get("password")).getBytes()));
        }catch (Exception e){
            return Result.fail("缺少参数");
        }
        if (null == userService.retrieveByPhone(user.getPhone())) {
            userService.add(user);
            String token = TokenUtils.getToken(user);
            Map<String, Object> userResult = new HashMap<>();
            userResult.put("id", user.getId());
            userResult.put("phone", user.getPhone());
            userResult.put("firstname", user.getFirstname());
            userResult.put("lastname", user.getLastname());
            userResult.put("email", user.getEmail());
            userResult.put("token", token);
            return Result.success("注册成功", userResult);
        }
        return Result.fail("注册失败");
    }
}
