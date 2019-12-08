package com.trip.review.repo;

import com.trip.review.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class reviewRepoTest {

    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ReviewPhotoRepo reviewPhotoRepo;

    @Test
    public void test() throws Exception {
        List<Review> reviewList = reviewRepo.findAll();
        if (reviewList != null) {
            for (Review review : reviewList) {
                System.out.println(review.getReviewId());
                reviewPhotoRepo.delete(review.getReviewPhotoList().get(0));
            }
        }
    }

    @Test
    public void update() throws Exception {
        List<Review> reviewList = reviewRepo.findAll();
        if (reviewList != null) {
            for (Review review : reviewList) {
                System.out.println(review.getReviewId());
                if (review.getReviewId().equals("jjaengge")) {
                    reviewRepo.updateReview("trip_content", review.getReviewSeq());
                }
            }
        }
    }
}


