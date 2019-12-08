package com.trip.review.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_point_audit_info")
public class PointAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_seq")
    private User user;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "ago_point", nullable = false)
    private int agoPoint;

    @Column(name = "current_point", nullable = false)
    private int currentPoint;

    @Column(name = "review_id", nullable = false)
    private String reviewId;

    @Column(name = "place_id", nullable = false)
    private String placeId;

    @Column(name = "create_at", nullable = false)
    private long createAt;

    public PointAudit() {

    }

    public PointAudit(Review review) {
        this.user = review.getUser();
        this.agoPoint = review.getUser().getUserPoint();
        this.placeId = review.getPlace().getPlaceId();
        this.reviewId = review.getReviewId();
        this.createAt = System.currentTimeMillis();
        this.reason = null;
        this.currentPoint = 0;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getAgoPoint() {
        return agoPoint;
    }

    public void setAgoPoint(int agoPoint) {
        this.agoPoint = agoPoint;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}