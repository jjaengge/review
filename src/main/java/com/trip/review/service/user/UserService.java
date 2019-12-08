package com.trip.review.service.user;

import com.trip.review.domain.ResponseData;

public interface UserService {
    ResponseData findUser(String userId);
}
