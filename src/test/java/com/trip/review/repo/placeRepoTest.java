package com.trip.review.repo;

import com.trip.review.entity.Place;
import com.trip.review.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest

public class placeRepoTest {

    @Autowired
    private PlaceRepo placeRepo;

    @Test
    public void test() throws Exception {
        List<Place> placeList = placeRepo.findAll();
        if (placeList != null) {
            for (Place place : placeList) {
                System.out.println(place.getPlaceId());
                if (Optional.ofNullable(place.getReviewList()).filter(v -> v.size() > 0).isPresent()) {
                    for (Review review : place.getReviewList()) {
                        System.out.println("======" + review.getReviewId());
                    }
                }
            }
        }
    }

}


