package com.server.hotel.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
}
