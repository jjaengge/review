package com.trip.review.controller;

import com.trip.review.domain.ResponseData;
import com.trip.review.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "유저 포인트 정보 조회")
    @GetMapping({"/{user-id}"})
    public ResponseEntity<ResponseData> getUserInfo(@PathVariable("user-id") String userId, HttpServletRequest request) {
        ResponseData responseData;
        responseData = userService.findUser(userId);
        return new ResponseEntity<>(responseData, responseData.getHttpStatus());
    }
}




