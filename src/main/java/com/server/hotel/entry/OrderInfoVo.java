package com.server.hotel.entry;

import lombok.Data;

@Data
public class OrderInfoVo extends OrderInfo{
    private String firstname;
    private String lastname;
    private String phone;

}
