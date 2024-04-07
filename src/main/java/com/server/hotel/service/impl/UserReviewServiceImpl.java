package com.server.hotel.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.server.hotel.entry.UserInfo;
import com.server.hotel.entry.UserReviews;
import com.server.hotel.mapper.UserReviewsMapper;
import com.server.hotel.service.UserReviewsService;
import org.springframework.stereotype.Service;

@Service
public class UserReviewServiceImpl extends MPJBaseServiceImpl<UserReviewsMapper, UserReviews> implements UserReviewsService {
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
