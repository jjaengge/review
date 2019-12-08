package com.trip.review.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tbl_review_info")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private int seq;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_seq")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_seq")
    private Place place;

    @Column(name = "review_id", nullable = false, unique = true)
    private String reviewId;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    public Review() {

    }

    public Review(User user, Place place) {
        this.user = user;
        this.place = place;
    }

    public int getReviewSeq() {
        return seq;
    }

    public void setReviewSeq(int reviewSeq) {
        this.seq = reviewSeq;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public List<ReviewPhoto> getReviewPhotoList() {
        return reviewPhotoList;
    }

    public void setReviewPhotoList(List<ReviewPhoto> reviewPhotoList) {
        this.reviewPhotoList = reviewPhotoList;
    }

    public void addReviewPhoto(ReviewPhoto reviewPhoto) {
        if (this.reviewPhotoList == null) {
            this.reviewPhotoList = new ArrayList<>();
        }
        this.reviewPhotoList.add(reviewPhoto);
    }

    public boolean existPhoto() {
        if (this.reviewPhotoList == null) {
            return false;
        } else {
            if (this.reviewPhotoList.size() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean existContent() {
        if (this.reviewContent == null) {
            return false;
        } else {
            if (this.reviewContent.length() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean existPhoto(String photoId) {
        if (existPhoto()) {
            for (ReviewPhoto reviewPhoto : reviewPhotoList) {
                if (reviewPhoto.getPhotoId().equals(photoId)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public List<ReviewPhoto> findReviewPhoto(List<String> photoIdList) {
        List<ReviewPhoto> retValue = new ArrayList<>();
        if (Optional.ofNullable(this.reviewPhotoList).filter(v -> v.size() > 0).isPresent()) {
            for (ReviewPhoto reviewPhoto : this.reviewPhotoList) {
                if (photoIdList.contains(reviewPhoto.getPhotoId())) {
                    retValue.add(reviewPhoto);
                }
            }
        }
        return retValue;
    }

    public List<ReviewPhoto> findExReviewPhoto(List<String> photoIdList) {
        List<ReviewPhoto> retValue = new ArrayList<>();

        if (Optional.ofNullable(photoIdList).filter(v -> v.size() > 0).isPresent()) {
            ReviewPhoto reviewPhoto;
            for (String photoId : photoIdList) {
                if (Optional.ofNullable(photoId)
                        .filter(v -> v.length() > 0)
                        .filter(v -> !existPhoto(v))
                        .isPresent()) {
                    reviewPhoto = new ReviewPhoto(this, photoId);
                    retValue.add(reviewPhoto);
                }
            }
        }
        return retValue;
    }

    public boolean equalsContentId(String contentId) {
        boolean retValue = false;
        if (Optional.ofNullable(contentId)
                .filter(v -> existContent())
                .filter(v -> v.equals(this.reviewContent))
                .isPresent()) {
            retValue = true;
        }
        return retValue;
    }
}