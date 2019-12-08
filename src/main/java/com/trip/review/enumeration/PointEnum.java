package com.trip.review.enumeration;

public enum PointEnum {
    POINT_FIRST_RIVIEW(5),
    POINT_FIRST_CONTENT(1),
    POINT_FIRST_PHOTO(1),
    POINT_DELETE_CONTENT(-1),
    POINT_DELETE_PHOTO(-1);

    public final int value;

    private PointEnum(int pointValue) {
        this.value = pointValue;
    }

    public boolean equals(int pointValue) {
        return (this.value == (pointValue));
    }

    public int get() {
        return this.value;
    }
}
