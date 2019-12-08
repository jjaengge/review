package com.trip.review.repo;

import com.trip.review.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Integer> {
    List<Place> findByPlaceId(String placeId);
}