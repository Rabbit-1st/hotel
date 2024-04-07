package com.server.hotel.entry;

import lombok.Data;

import java.util.List;

@Data
public class RoomInfoVo extends RoomInfo {
    private List<RoomImages> roomImagesList;

}
