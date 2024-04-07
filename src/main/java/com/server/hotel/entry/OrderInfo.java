package com.server.hotel.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String orderId;
    private String userId;
    private String roomType;
    private String status;
    private Date createTime;
    private Date checkInDate;
    private Date checkOutDate;
    private Date cancelTime;
}
