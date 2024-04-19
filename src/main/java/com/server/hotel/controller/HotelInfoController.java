package com.server.hotel.controller;

import com.server.hotel.common.Result;
import com.server.hotel.entry.HotelInfo;
import com.server.hotel.service.impl.HotelInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("hotel")
public class HotelInfoController {
    @Autowired
    HotelInfoServiceImpl hotelInfoService;

    @GetMapping("hotelInfo")
    public Result<?> getHotelInfo() {
        HotelInfo hotelInfo = hotelInfoService.getHotelInfo();
        return Result.success("查询成功", hotelInfo);
    }

    @PostMapping("updateHotelInfo")
    public Result<?> updateHotelInfo(@RequestBody HotelInfo hotelInfo) {
       if( hotelInfoService.updateHotelInfo(hotelInfo)){
           return Result.success("修改成功", hotelInfo);
       }else {
           return Result.fail("修改失败");
       }

    }
}
