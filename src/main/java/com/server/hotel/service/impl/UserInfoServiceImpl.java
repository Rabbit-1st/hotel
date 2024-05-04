package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.server.hotel.entry.UserInfo;
import com.server.hotel.mapper.UserInfoMapper;
import com.server.hotel.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserInfoServiceImpl extends MPJBaseServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    public boolean add(UserInfo user) {
        return this.save(user);

    }

    public UserInfo remove() {
        return null;
    }

    public boolean edit(UserInfo user) {
        return this.updateById(user);
    }

    public UserInfo retrieve(String id) {
        return this.getById(id);
    }

    public UserInfo login(String phone, String password) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone, phone);
        queryWrapper.eq(UserInfo::getPassword, DigestUtils.md5DigestAsHex(password.getBytes()));
        return this.getOne(queryWrapper);
    }

    public UserInfo retrieveByPhone(String phone) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getPhone, phone);
        return this.getOne(queryWrapper);
    }

    public UserInfo getUserInfo(String id) {
        return this.getById(id);
    }
}
