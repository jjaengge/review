package com.trip.review.domain;

import com.trip.review.entity.PointAudit;
import com.trip.review.entity.Review;
import com.trip.review.enumeration.PointEnum;

import java.util.ArrayList;
import java.util.List;

public class PointInfos {
    public List<PointAudit> getPointAuditList() {
        return pointAuditList;
    }

    public void setPointAuditList(List<PointAudit> pointAuditList) {
        this.pointAuditList = pointAuditList;
    }

    List<PointAudit> pointAuditList;

    public PointInfos() {
        pointAuditList = new ArrayList<>();
    }

    public void checkPointEvent(PointEnum pointEnum, Review review) {

        PointAudit pointAudit = new PointAudit(review);

        if (pointEnum == PointEnum.POINT_FIRST_CONTENT) {
            pointAudit.setReason("POINT_FIRST_CONTENT");
            review.getUser().setUserPoint(review.getUser().getUserPoint() + pointEnum.get());
            System.out.println(String.format("user[%s] 가 place[%s]에 첫 리뷰글을 생성 해서 포인트[%d]가 추가 증정 됩니다.",
                    review.getUser().getUserId(),
                    review.getPlace().getPlaceId(),
                    pointEnum.get()));
        } else if (pointEnum == PointEnum.POINT_FIRST_PHOTO) {
            pointAudit.setReason("POINT_FIRST_PHOTO");
            review.getUser().setUserPoint(review.getUser().getUserPoint() + pointEnum.get());
            System.out.println(String.format("user[%s] 가 place[%s]에 첫 사진을 생성 해서 포인트[%d]가 추가 증정 됩니다.",
                    review.getUser().getUserId(),
                    review.getPlace().getPlaceId(),
                    pointEnum.get()));
        } else if (pointEnum == PointEnum.POINT_FIRST_RIVIEW) {
            pointAudit.setReason("POINT_FIRST_RIVIEW");
            review.getUser().setUserPoint(review.getUser().getUserPoint() + pointEnum.get());
            System.out.println(String.format("user[%s] 가 place[%s]에 첫 리뷰를 생성 해서 특별 포인트[%d]가 추가 증정 됩니다.",
                    review.getUser().getUserId(), review.getPlace().getPlaceId(), pointEnum.get()));
        } else if (pointEnum == PointEnum.POINT_DELETE_PHOTO) {
            pointAudit.setReason("POINT_DELETE_PHOTO");
            review.getUser().setUserPoint(review.getUser().getUserPoint() + pointEnum.get());
            System.out.println(String.format("user[%s] 가 place[%s]에 사진을 모두 삭제 해서 포인트[%d] 처리 됐습니다.",
                    review.getUser().getUserId(), review.getPlace().getPlaceId(), pointEnum.get()));
        } else if (pointEnum == PointEnum.POINT_DELETE_CONTENT) {
            pointAudit.setReason("POINT_DELETE_CONTENT");
            review.getUser().setUserPoint(review.getUser().getUserPoint() + pointEnum.get());
            System.out.println(String.format("user[%s] 가 place[%s]에 댓글을 모두 삭제 해서 포인트[%d] 처리 됐습니다.",
                    review.getUser().getUserId(), review.getPlace().getPlaceId(), pointEnum.get()));
        }

        if (pointAudit.getReason() != null) {
            pointAudit.setCurrentPoint(review.getUser().getUserPoint());
            this.pointAuditList.add(pointAudit);
        }
    }
}
