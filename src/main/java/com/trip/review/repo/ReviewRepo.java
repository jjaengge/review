package com.trip.review.repo;

import com.trip.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
    List<Review> findByReviewId(String reviewId);

    List<Review> findByUserSeqAndPlaceSeq(int userSeq, int placeSeq);

    @Transactional
    @Modifying
    @Query("UPDATE Review rv set rv.reviewContent =:content  where rv.seq =:seq")
    void updateReview(@Param("content") String content, @Param("seq") int seq);
}