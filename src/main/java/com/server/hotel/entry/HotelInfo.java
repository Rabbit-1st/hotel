package com.server.hotel.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class HotelInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String hotelId;
    private String hotelName;
    private String address;
    private String contact;
    private String baseInfo;
    private String intro;
    private String edTime;
    private String pets;
    private String foreignInfo;
    private String dietary;
    private String parking;
    private String network;
    private String service;
    private String electrification;

}
