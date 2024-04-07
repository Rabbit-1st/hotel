package com.server.hotel.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.server.hotel.entry.RoomImages;
import com.server.hotel.entry.UserInfo;
import com.server.hotel.mapper.RoomImagesMapper;
import com.server.hotel.service.RoomImagesService;

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
}
