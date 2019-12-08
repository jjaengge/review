package com.trip.review.service.event;

import com.trip.review.domain.EventData;
import com.trip.review.domain.ResponseData;
import com.trip.review.enumeration.EventTypeEnum;
import com.trip.review.enumeration.ResponseMsgEnum;
import com.trip.review.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("eventServiceImpl")
public class EventServiceImpl implements EventService {

    @Autowired
    private ReviewService reviewService;

    @Override
    public ResponseData evnetHandle(EventData eventData) {
        ResponseData responseData;

        if (eventData.getType().toLowerCase().equals(EventTypeEnum.REVIEW_EVENT_TYPE.get())) {
            responseData = reviewService.reviewHandle(eventData);
        } else {
            responseData = ResponseMsgEnum.RES_BAD_REQ_TYPE.get();
        }
        return responseData;
    }
}
