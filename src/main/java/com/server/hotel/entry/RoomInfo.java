package com.server.hotel.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class RoomInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String roomId;
    private String name;
    private int bedType;//0大床，1双人床，2，单人床
    private int breakfast;//0无，1有
    private int wifi;//0无，1有
    private int number;
    private int price;
    private int people;
    private int policy;//0免费取消，1不可取消
    private int numberMax;
    @TableField(exist = false)
    private List<RoomImages> images;
}
