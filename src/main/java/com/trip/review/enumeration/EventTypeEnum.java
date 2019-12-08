package com.trip.review.enumeration;

public enum EventTypeEnum {
    REVIEW_EVENT_TYPE("review");

    public final String value;

    private EventTypeEnum(String typeValue) {
        this.value = typeValue;
    }

    public boolean equals(String typeValue) {
        return (this.value == (typeValue));
    }

    public String get() {
        return this.value;
    }
}
