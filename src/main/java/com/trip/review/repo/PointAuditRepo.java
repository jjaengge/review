package com.trip.review.repo;

import com.trip.review.entity.PointAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointAuditRepo extends JpaRepository<PointAudit, Integer> {
    List<PointAudit> findByUserSeq(int userSeq);
}