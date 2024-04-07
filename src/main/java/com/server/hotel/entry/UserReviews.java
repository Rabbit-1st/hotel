package com.server.hotel.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserReviews {
    @TableId(type = IdType.ASSIGN_ID)
    private String reviewId;
    private String userId;
    private String hotelId;
    private int rating;
    private int reviewText;

}
