package com.server.hotel.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class HotelImages {
    @TableId(type = IdType.ASSIGN_ID)
    private String imageId;
    private String url;
    private String imageClass;
    @TableField(exist = false)
    private String name;
}
