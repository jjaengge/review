package com.trip.review.repo;

import com.trip.review.entity.PointAudit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class pointAudotRepoTest {

    @Autowired
    private PointAuditRepo pointAuditRepo;

    @Test
    public void test() throws Exception {
        List<PointAudit> pointAuditList = pointAuditRepo.findByUserSeq(1);

        if (pointAuditList != null) {
            for (PointAudit pointAudit : pointAuditList) {
                System.out.println(pointAudit.getReason());
            }
        }
    }
}


