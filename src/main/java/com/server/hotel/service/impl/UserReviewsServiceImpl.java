package com.server.hotel.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.server.hotel.entry.*;
import com.server.hotel.mapper.UserReviewsMapper;
import com.server.hotel.service.UserReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReviewsServiceImpl extends MPJBaseServiceImpl<UserReviewsMapper, UserReviews> implements UserReviewsService {


    public IPage<UserReviewsVo> pageList(long current, long size, String order, String prop, int rating) {
        Page<UserReviewsVo> page = new Page<>(current, size);
        if (order.equals("descending")) {
            order = "DESC";
        } else if (order.equals("ascending")) {
            order = "ASC";
        }
        MPJLambdaWrapper<UserReviews> queryWrapper = new MPJLambdaWrapper<>(UserReviews.class);
        queryWrapper.selectAll(UserReviews.class);

        if (rating > 3) {
            queryWrapper.eq(UserReviews::getRating, 5)
                    .or().eq(UserReviews::getRating, 4);
        } else if (rating == 3) {
            queryWrapper.eq(UserReviews::getRating, 3);
        } else if (rating > 0) {
            queryWrapper.eq(UserReviews::getRating, 2)
                    .or().eq(UserReviews::getRating, 1);
        }


        queryWrapper
                .last("ORDER BY " + prop + " " + order)
                .select(UserInfo::getFirstname, UserInfo::getLastname)
                .leftJoin(UserInfo.class, UserInfo::getId, UserReviews::getUserId);
        return this.selectJoinListPage(page, UserReviewsVo.class, queryWrapper);

    }

    public boolean add(UserReviews userReviews) {
        return this.save(userReviews);

    }

    public UserInfo remove() {
        return null;
    }

    public boolean edit(UserReviews userReviews) {
        return this.updateById(userReviews);
    }

    public UserReviews retrieve(String id) {
        return this.getById(id);
    }
}
