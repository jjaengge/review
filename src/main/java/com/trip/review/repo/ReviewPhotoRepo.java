package com.trip.review.repo;

import com.trip.review.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepo extends JpaRepository<ReviewPhoto, Integer> {
    List<ReviewPhoto> findByPhotoId(String photoId);
}