package com.trip.review.service.repository;


import com.trip.review.entity.Place;
import com.trip.review.entity.PointAudit;
import com.trip.review.entity.Review;
import com.trip.review.entity.User;

import java.util.List;

public interface RepoService {

    User findUser(String userId, boolean createFlag);

    Place findPlace(String placeId, boolean createFlag);

    Review findReview(String reviewId, String userId, String placeId);

    boolean existReviewOfPlace(Place place);

    boolean existReview(User user, Place place);

    boolean existReview(String reviewId);

    void saveRevieAndPhoto(Review review, boolean existPhoto);

    void updateReviewAndPhoto(Review review, boolean existPhoto);

    void deleteReviewAndPhoto(Review review);

    void updateReviewAndDeletePhoto(Review review, boolean existPhoto);

    void savePointAudit(PointAudit pointAudit);

    List<PointAudit> findPointAudits(int userSeq);
}
