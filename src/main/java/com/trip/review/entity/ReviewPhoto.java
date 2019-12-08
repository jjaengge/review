package com.trip.review.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "tbl_review_photo_info",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"review_seq", "photo_id"})
)

public class ReviewPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "review_seq")
    private Review review;

    @Column(name = "photo_id", nullable = false, unique = true)
    private String photoId;

    public ReviewPhoto() {

    }

    public ReviewPhoto(Review review, String photoId) {
        this.review = review;
        this.photoId = photoId;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

}