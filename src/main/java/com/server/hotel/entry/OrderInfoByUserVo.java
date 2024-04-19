package com.server.hotel.entry;

import lombok.Data;

@Data
public class OrderInfoByUserVo extends OrderInfo{
    private String firstname;
    private String lastname;
    private String phone;
    private String roomName;
    private int bedType;//0大床，1双人床，2，单人床
    private int breakfast;//0无，1有
    private int wifi;//0无，1有
    private int people;
    private int policy;//0免费取消，1不可取消
}
