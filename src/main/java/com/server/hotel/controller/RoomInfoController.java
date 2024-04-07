package com.server.hotel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.server.hotel.common.Result;
import com.server.hotel.entry.RoomInfo;
import com.server.hotel.entry.RoomInfoVo;
import com.server.hotel.service.impl.RoomInfoServiceImpl;
import com.server.hotel.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("room")
public class RoomInfoController {
    @Autowired
    RoomInfoServiceImpl roomInfoService;

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
        System.out.println(current + "," + size + "," + order + "," + prop + "," + text);
        IPage<RoomInfo> list = roomInfoService.pageList(current, size, order, prop, text);
        return Result.success("查询成功", list);
    }

}
