package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.server.hotel.entry.HotelInfo;
import com.server.hotel.mapper.HotelInfoMapper;
import com.server.hotel.service.HotelInfoService;
import org.springframework.stereotype.Service;

@Service
public class HotelInfoServiceImpl extends MPJBaseServiceImpl<HotelInfoMapper, HotelInfo> implements HotelInfoService {

    public HotelInfo getHotelInfo() {
        LambdaQueryWrapper<HotelInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.last("limit 1");
        return this.getOne(queryWrapper);
    }

    public boolean updateHotelInfo(HotelInfo hotelInfo) {
        return this.updateById(hotelInfo);
    }
}
