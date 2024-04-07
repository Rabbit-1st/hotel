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
    private int breakfast;
    private int wifi;
    private int number;
    private int price;
    private String people;
    private String policy;
}
