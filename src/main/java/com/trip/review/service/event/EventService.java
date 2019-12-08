package com.trip.review.service.event;

import com.trip.review.domain.EventData;
import com.trip.review.domain.ResponseData;

public interface EventService {
    ResponseData evnetHandle(EventData eventData);
}
