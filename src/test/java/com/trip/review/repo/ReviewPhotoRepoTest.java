package com.trip.review.repo;

import com.trip.review.entity.ReviewPhoto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ReviewPhotoRepoTest {

    @Autowired
    private ReviewPhotoRepo reviewPhotoRepo;

    @Test
    public void test() throws Exception {
        List<ReviewPhoto> reviewPhotoList = reviewPhotoRepo.findAll();

        if (reviewPhotoList != null) {
            for (ReviewPhoto reviewPhoto : reviewPhotoList) {
                System.out.println(reviewPhoto.getPhotoId());
                reviewPhotoRepo.delete(reviewPhoto);
            }
        }
    }
}


