package com.trip.review.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_place_info")
public class Place implements Serializable {

    private static final long serialVersionUID = 2852151781985418871L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;
    @Column(name = "place_id", nullable = false, unique = true)
    private String placeId;
    @OneToMany(mappedBy = "place", fetch = FetchType.EAGER)
    private List<Review> reviewList = new ArrayList<>();

    public Place() {

    }

    public Place(String placeId) {
        this.placeId = placeId;
    }

    public int getPlaceSeq() {
        return seq;
    }

    public void setPlaceSeq(int placeSeq) {
        this.seq = placeSeq;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}