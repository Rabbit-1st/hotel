package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.server.hotel.entry.*;
import com.server.hotel.mapper.OrderInfoMapper;
import com.server.hotel.service.OrderInfoService;

import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends MPJBaseServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    public IPage<OrderInfoVo> pageList(long current, long size, String order, String prop, String status, String phone) {
        Page<OrderInfoVo> page = new Page<>(current, size);
        if (order.equals("descending")) {
            order = "DESC";
        } else if (order.equals("ascending")) {
            order = "ASC";
        }
        MPJLambdaWrapper<OrderInfo> queryWrapper = new MPJLambdaWrapper<>(OrderInfo.class);
        queryWrapper.selectAll(OrderInfo.class)
                .or().eq(OrderInfo::getStatus, status)
                .like(UserInfo::getPhone, phone)
                .last("ORDER BY " + prop + " " + order)
                .select(UserInfo::getFirstname, UserInfo::getLastname, UserInfo::getPhone)
                .leftJoin(UserInfo.class, UserInfo::getId, OrderInfo::getUserId)
                .selectAs(RoomInfo::getName, OrderInfoVo::getRoomName)
                .leftJoin(RoomInfo.class, RoomInfo::getRoomId, OrderInfo::getRoomType);
        return this.selectJoinListPage(page, OrderInfoVo.class, queryWrapper);

    }

    public boolean edit(String orderId, int status) {
        OrderInfo orderInfo = this.getById(orderId);
        if (null == orderInfo) {
            return false;
        }
        orderInfo.setStatus(status);
        return this.updateById(orderInfo);
    }

    public boolean add(OrderInfo orderInfo) {
        return this.save(orderInfo);

    }

    public IPage<OrderInfoByUserVo> pageListByUser(long current, long size, String order, String prop, String status, String phone,String userId) {
        Page<OrderInfoByUserVo> page = new Page<>(current, size);
        if (order.equals("descending")) {
            order = "DESC";
        } else if (order.equals("ascending")) {
            order = "ASC";
        }
        MPJLambdaWrapper<OrderInfo> queryWrapper = new MPJLambdaWrapper<>(OrderInfo.class);
        queryWrapper.selectAll(OrderInfo.class)
                .eq(OrderInfo::getUserId,userId)
                .or().eq(OrderInfo::getStatus, status)
                .like(UserInfo::getPhone, phone)
                .last("ORDER BY " + prop + " " + order)
                .select(UserInfo::getFirstname, UserInfo::getLastname, UserInfo::getPhone)
                .leftJoin(UserInfo.class, UserInfo::getId, OrderInfo::getUserId)
                .selectAs(RoomInfo::getName, OrderInfoVo::getRoomName)
                .select(RoomInfo::getBedType,RoomInfo::getBreakfast,RoomInfo::getWifi,RoomInfo::getPeople,RoomInfo::getPolicy)
                .leftJoin(RoomInfo.class, RoomInfo::getRoomId, OrderInfo::getRoomType);
        return this.selectJoinListPage(page, OrderInfoByUserVo.class, queryWrapper);

    }

}
