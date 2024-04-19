package com.server.hotel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.server.hotel.common.Result;
import com.server.hotel.entry.RoomImages;
import com.server.hotel.entry.RoomInfo;

import com.server.hotel.service.impl.RoomImagesServiceImpl;
import com.server.hotel.service.impl.RoomInfoServiceImpl;
import com.server.hotel.utils.ServletUtils;
import com.server.hotel.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("room")
public class RoomInfoController {
    @Autowired
    RoomInfoServiceImpl roomInfoService;
    @Autowired
    RoomImagesServiceImpl roomImagesService;

    @GetMapping("list")
    public Result<IPage<RoomInfo>> list(@RequestParam Map<String, Object> request) {
        long current;
        long size;
        String order;
        String prop;
        String text;
        if (null != request.get("order")) {
            order = (String) request.get("order");
        } else {
            order = "descending";
        }
        if (null != request.get("prop")) {
            prop = (String) request.get("prop");
        } else {
            prop = "number";
        }
        if (null != request.get("text")) {
            text = (String) request.get("text");
        } else {
            text = "";
        }


        try {
            current = Long.parseLong((String) request.get("current"));
            size = Long.parseLong((String) request.get("size"));
        } catch (Exception e) {
            return Result.fail("参数错误");
        }


        prop = StrUtils.camelToUnderline(prop);

        IPage<RoomInfo> list = roomInfoService.pageList(current, size, order, prop, text);
        return Result.success("查询成功", list);
    }

    @PostMapping("update")
    public Result<?> update(@RequestBody RoomInfo request) {
        if (roomInfoService.edit(request)) {
            return Result.success("修改成功", null);
        }
        return Result.fail("错误");
    }

    @PostMapping("add")
    public Result<?> add(@RequestBody RoomInfo request) {
        if (roomInfoService.add(request)) {
            return Result.success("添加成功", null);
        }
        return Result.fail("错误");
    }

    @PostMapping("remove")
    public Result<?> remove(@RequestBody Map<String, Object> request) {
        List<String> ids;
        try {
            ids = (List<String>) request.get("ids");
        } catch (Exception e) {
            return Result.fail("参数错误");
        }

        if (roomInfoService.remove(ids)) {
            return Result.success("删除成功", null);
        }
        return Result.fail("错误");
    }

    @GetMapping("listClient")
    public Result<IPage<RoomInfo>> listClient(@RequestParam Map<String, Object> request) {
        long current;
        long size;
        String order;
        String prop;
        long bedType = Long.parseLong((String) request.get("bedType"));
        long breakfast = Long.parseLong((String) request.get("breakfast"));
        long policy = Long.parseLong((String) request.get("policy"));
        long wifi = Long.parseLong((String) request.get("wifi"));
        if (null != request.get("order")) {
            order = (String) request.get("order");
        } else {
            order = "descending";
        }
        if (null != request.get("prop")) {
            prop = (String) request.get("prop");
        } else {
            prop = "number";
        }


        try {
            current = Long.parseLong((String) request.get("current"));
            size = Long.parseLong((String) request.get("size"));
        } catch (Exception e) {
            return Result.fail("参数错误");
        }


        prop = StrUtils.camelToUnderline(prop);

        IPage<RoomInfo> list = roomInfoService.pageListClass(current, size, order, prop, bedType, breakfast, policy, wifi);

        for (RoomInfo roomInfo : list.getRecords()) {
            roomInfo.setImages(roomImagesService.getImageList(roomInfo.getRoomId()));

            for (RoomImages roomImages : roomInfo.getImages()) {
                roomImages.setName(roomImages.getImageUrl());
                roomImages.setUrl(ServletUtils.getImageUrl(roomImages.getImageUrl()));
            }
        }


        return Result.success("查询成功", list);
    }

    @GetMapping("getRoomInfo")
    public Result<?> getRoomInfo(@RequestParam Map<String, Object> request) {
        String roomId = (String) request.get("roomId");
        RoomInfo roomInfo = roomInfoService.getRoomInfo(roomId);
        return Result.success("查询成功", roomInfo);
    }

}
