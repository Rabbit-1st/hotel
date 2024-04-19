package com.server.hotel.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.server.hotel.common.Result;
import com.server.hotel.entry.UserReviews;
import com.server.hotel.entry.UserReviewsVo;
import com.server.hotel.service.impl.UserReviewsServiceImpl;
import com.server.hotel.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("reviews")
public class UserReviewsController {
    @Autowired
    UserReviewsServiceImpl userReviewService;

    @GetMapping("list")
    public Result<?> list(@RequestParam Map<String, Object> request) {
        long current;
        long size;
        String order;
        String prop;
        int rating;
        if (null != request.get("order")) {
            order = (String) request.get("order");
        } else {
            order = "descending";
        }
        if (null != request.get("prop")) {
            prop = (String) request.get("prop");
        } else {
            prop = "createTime";
        }


        try {
            current = Long.parseLong((String) request.get("current"));
            size = Long.parseLong((String) request.get("size"));
            rating = Integer.parseInt((String) request.get("rating"));
        } catch (Exception e) {
            return Result.fail("参数错误" + e);
        }


        prop = StrUtils.camelToUnderline(prop);


        IPage<UserReviewsVo> userReviews = userReviewService.pageList(current, size, order, prop, rating);

        return Result.success("查询成功", userReviews);
    }

    @PostMapping("add")
    public Result<?> add(@RequestBody Map<String, Object> request) {
        String userId;
        int rating;
        String reviewText;
        Date createTime;
        try {
            userId = (String) request.get("userId");
            rating =(int) request.get("rating");
            reviewText = (String) request.get("reviewText");
            createTime = (Date) request.get("createTime");
            if(null == createTime){
                createTime = new Date();
            }
        } catch (Exception e) {
            return Result.fail("参数错误" + e);
        }


        UserReviews userReviews =new UserReviews();
        userReviews.setUserId(userId);
        userReviews.setRating(rating);
        userReviews.setReviewText(reviewText);
        userReviews.setCreateTime(createTime);

        userReviewService.add(userReviews);

        return Result.success("评论成功",null);
    }

}
