package com.trip.review.repo;

import com.trip.review.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByUserId(String userId);
}