package com.trip.review.service.review;

import com.trip.review.domain.EventData;
import com.trip.review.domain.ResponseData;

public interface ReviewService {
    ResponseData reviewHandle(EventData eventData);

    ResponseData addReview(EventData eventData);

    ResponseData modifyReview(EventData eventData);

    ResponseData deleteReview(EventData eventData);
}
