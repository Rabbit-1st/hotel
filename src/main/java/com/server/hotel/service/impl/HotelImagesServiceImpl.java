package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.server.hotel.entry.HotelImages;
import com.server.hotel.mapper.HotelImagesMapper;
import com.server.hotel.service.HotelImagesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelImagesServiceImpl extends MPJBaseServiceImpl<HotelImagesMapper, HotelImages> implements HotelImagesService {

    public boolean upload(String newName) {
        System.out.println(newName);
        HotelImages hotelImages = new HotelImages();
        hotelImages.setUrl(newName);
        hotelImages.setImageClass("默认");
        return this.save(hotelImages);

    }

    public List<HotelImages> getList() {
        return this.list();
    }

    public boolean deleteImage(String fileName) {
        System.out.println(fileName);
        LambdaQueryWrapper<HotelImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HotelImages::getUrl, fileName);
        return this.remove(queryWrapper);
    }
}
