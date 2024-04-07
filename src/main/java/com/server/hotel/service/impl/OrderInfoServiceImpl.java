package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.server.hotel.entry.OrderInfo;
import com.server.hotel.entry.OrderInfoVo;
import com.server.hotel.entry.UserInfo;
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
                .leftJoin(UserInfo.class, UserInfo::getId, OrderInfo::getUserId);
        return this.selectJoinListPage(page, OrderInfoVo.class, queryWrapper);

    }

    public boolean edit(String orderId, String status) {
        OrderInfo orderInfo = this.getById(orderId);
        if (null == orderInfo) {
            return false;
        }
        orderInfo.setStatus(status);
        return this.updateById(orderInfo);
    }

}
