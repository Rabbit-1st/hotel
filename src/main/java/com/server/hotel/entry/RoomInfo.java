package com.server.hotel.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class RoomInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String roomId;
    private String name;
    private String bedType;
    private int breakfast;//0无，1有
    private int wifi;//0无，1有
    private int number;
    private int price;
    private String people;
    private String policy;
    private String numberMax;
}
