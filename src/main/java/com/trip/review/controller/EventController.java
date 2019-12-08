package com.trip.review.controller;

import com.trip.review.domain.EventData;
import com.trip.review.domain.ResponseData;
import com.trip.review.enumeration.ResponseMsgEnum;
import com.trip.review.service.event.EventService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    @Autowired
    EventService eventService;

    @ApiOperation(value = "리뷰 등록 api")
    @PostMapping({"/"})
    public ResponseEntity<ResponseData> regiReview(@RequestBody EventData eventData, HttpServletRequest request) {
        ResponseData responseData;
        if (Optional.ofNullable(eventData)
                .filter(v -> !v.isMalformed())
                .isPresent()) {
            responseData = eventService.evnetHandle(eventData);
        } else {
            responseData = ResponseMsgEnum.RES_BAD_REQ_MALFORMED_JSON.get();
        }
        return new ResponseEntity<>(responseData, responseData.getHttpStatus());
    }

}

