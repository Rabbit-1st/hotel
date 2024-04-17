package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.server.hotel.entry.*;
import com.server.hotel.mapper.RoomInfoMapper;
import com.server.hotel.service.RoomInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomInfoServiceImpl extends MPJBaseServiceImpl<RoomInfoMapper, RoomInfo> implements RoomInfoService {
    public boolean add(RoomInfo roomInfo) {
        return this.save(roomInfo);

    }

    public boolean remove(List<String> ids) {
        return this.removeByIds(ids);
    }

    public boolean edit(RoomInfo roomInfo) {
        return this.updateById(roomInfo);
    }

    public RoomInfo retrieve(String id) {
        return this.getById(id);
    }

    public IPage<RoomInfo> pageList(long current, long size, String order, String prop, String text) {
        Page<RoomInfo> page = new Page<>(current, size);
        if (order.equals("descending")) {
            order = "DESC";
        } else if (order.equals("ascending")) {
            order = "ASC";
        }
        MPJLambdaWrapper<RoomInfo> queryWrapper = new MPJLambdaWrapper<>(RoomInfo.class);
        queryWrapper.selectAll(RoomInfo.class)
                .like(RoomInfo::getBedType, text)
                .or().like(RoomInfo::getName, text)
                .last("ORDER BY " + prop + " " + order);
        return this.selectJoinListPage(page, RoomInfo.class, queryWrapper);

    }
}
