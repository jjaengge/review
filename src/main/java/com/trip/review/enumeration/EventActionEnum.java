package com.trip.review.enumeration;

public enum EventActionEnum {
    ADD_EVENT_ACTION("add"),
    MODIFY_EVENT_ACTION("mod"),
    DELETE_EVENT_ACTION("delete");

    public final String value;

    private EventActionEnum(String actionValue) {
        this.value = actionValue;
    }

    public boolean equals(String actionValue) {
        return (this.value == (actionValue));
    }

    public String get() {
        return this.value;
    }
}
