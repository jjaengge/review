package com.trip.review.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_user_info")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;
    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;
    @Column(name = "user_point", nullable = false)
    private int userPoint;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Review> reviewList = new ArrayList<>();

    public User() {

    }

    public User(String userId, int userPoint) {
        this.userId = userId;
        this.userPoint = userPoint;
    }

    public int getUserSeq() {
        return seq;
    }

    public void setUserSeq(int userSeq) {
        this.seq = userSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}