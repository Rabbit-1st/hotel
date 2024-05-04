package com.server.hotel.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.server.hotel.common.Result;
import com.server.hotel.entry.OrderInfo;
import com.server.hotel.entry.OrderInfoByUserVo;
import com.server.hotel.entry.OrderInfoVo;
import com.server.hotel.service.impl.OrderInfoServiceImpl;


import com.server.hotel.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("order")
public class OrderInfoController {
    @Autowired
    OrderInfoServiceImpl orderService;

    @GetMapping("list")
    public Result<IPage<OrderInfoVo>> list(@RequestParam Map<String, Object> request) {
        long current;
        long size;
        String order;
        String prop;
        String status;
        String phone;
        if (null != request.get("order")) {
            order = (String) request.get("order");
        } else {
            order = "descending";
        }
        if (null != request.get("prop")) {
            prop = (String) request.get("prop");
        } else {
            prop = "createTime";
        }
        if (null != request.get("phone")) {
            phone = (String) request.get("phone");
        } else {
            phone = "";
        }


        try {
            current = Long.parseLong((String) request.get("current"));
            size = Long.parseLong((String) request.get("size"));
            status = (String) request.get("status");
        } catch (Exception e) {
            return Result.fail("参数错误");
        }


        prop = StrUtils.camelToUnderline(prop);

        IPage<OrderInfoVo> list = orderService.pageList(current, size, order, prop, status, phone);
        return Result.success("查询成功", list);
    }

    @PostMapping("update")
    public Result<?> update(@RequestBody Map<String, Object> request) {
        String orderId = (String) request.get("orderId");
        int status = (int) request.get("status");

        if (orderService.edit(orderId, status)) {
            return Result.success("修改成功", null);
        }
        return Result.fail("错误");
    }

    @PostMapping("add")
    public Result<?> add(@RequestBody Map<String, Object> request) {
        String userId;
        String roomType;
        int status;
        int totalPrice;
        Date createTime = new Date();
        Date checkInDate;
        Date checkOutDate;




        userId = (String) request.get("userId");
        roomType = (String) request.get("roomType");
        status = 0;
        totalPrice = (int) request.get("totalPrice");
        try {
            checkInDate = new Date((long) request.get("checkInDate"));
            checkOutDate = new Date((long) request.get("checkOutDate"));
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setUserId(userId);
            orderInfo.setRoomType(roomType);
            orderInfo.setStatus(status);
            orderInfo.setTotalPrice(totalPrice);
            orderInfo.setCreateTime(createTime);
            orderInfo.setCancelTime(null);
            orderInfo.setCheckInDate(checkInDate);
            orderInfo.setCheckOutDate(checkOutDate);
            if (orderService.add(orderInfo)) {
                return Result.success("成功提交订单", null);
            }
            return Result.fail("订单提交失败");
        } catch (Exception e) {
            return Result.fail("参数错误");
        }


    }

    @GetMapping("listByUser")
    public Result<IPage<OrderInfoByUserVo>> listByUser(@RequestParam Map<String, Object> request) {
        long current;
        long size;
        String order;
        String prop;
        String status;
        String phone;
        String userId;
        if (null != request.get("order")) {
            order = (String) request.get("order");
        } else {
            order = "descending";
        }
        if (null != request.get("prop")) {
            prop = (String) request.get("prop");
        } else {
            prop = "createTime";
        }
        if (null != request.get("phone")) {
            phone = (String) request.get("phone");
        } else {
            phone = "";
        }


        try {
            current = Long.parseLong((String) request.get("current"));
            size = Long.parseLong((String) request.get("size"));
            status = (String) request.get("status");
            userId = (String) request.get("userId");
        } catch (Exception e) {
            return Result.fail("参数错误");
        }


        prop = StrUtils.camelToUnderline(prop);

        IPage<OrderInfoByUserVo> list = orderService.pageListByUser(current, size, order, prop, status, phone, userId);
        return Result.success("查询成功", list);
    }

}
