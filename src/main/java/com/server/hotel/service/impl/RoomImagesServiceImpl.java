package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;

import com.server.hotel.entry.RoomImages;
import com.server.hotel.entry.UserInfo;
import com.server.hotel.mapper.RoomImagesMapper;
import com.server.hotel.service.RoomImagesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomImagesServiceImpl extends MPJBaseServiceImpl<RoomImagesMapper, RoomImages> implements RoomImagesService {
    public boolean add(RoomImages roomImages) {
        return this.save(roomImages);

    }

    public UserInfo remove() {
        return null;
    }

    public boolean edit(RoomImages roomImages) {
        return this.updateById(roomImages);
    }

    public RoomImages retrieve(String id) {
        return this.getById(id);
    }

    public boolean upload(String newName, String roomId) {
        RoomImages roomImages = new RoomImages();
        roomImages.setImageUrl(newName);
        roomImages.setRoomId(roomId);
        return this.save(roomImages);

    }

    public List<RoomImages> getList(String roomId) {
        LambdaQueryWrapper<RoomImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomImages::getRoomId, roomId);
        return this.list(queryWrapper);
    }

    public boolean deleteImage(String fileName) {
        LambdaQueryWrapper<RoomImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomImages::getImageUrl, fileName);
        return this.remove(queryWrapper);
    }

    public List<RoomImages> getImageList(String roomId) {
        LambdaQueryWrapper<RoomImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoomImages::getRoomId, roomId);
        return this.list(queryWrapper);
    }
}
