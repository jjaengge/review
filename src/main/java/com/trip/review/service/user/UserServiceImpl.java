package com.trip.review.service.user;

import com.trip.review.domain.ResponseData;
import com.trip.review.entity.User;
import com.trip.review.enumeration.ResponseMsgEnum;
import com.trip.review.service.repository.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private RepoService repoService;

    @Override
    public ResponseData findUser(String userId) {
        User user = repoService.findUser(userId, false);
        if (user == null) {
            return ResponseMsgEnum.RES_USER_NOT_FOUND.get();
        } else {
            return new ResponseData(String.format("user[%s] point[%d]", user.getUserId(), user.getUserPoint()), HttpStatus.OK);
        }
    }
}
