package com.trip.review.service.review;

import com.trip.review.domain.EventData;
import com.trip.review.domain.PointInfoQueue;
import com.trip.review.domain.PointInfos;
import com.trip.review.domain.ResponseData;
import com.trip.review.entity.*;
import com.trip.review.enumeration.EventActionEnum;
import com.trip.review.enumeration.PointEnum;
import com.trip.review.enumeration.ResponseMsgEnum;
import com.trip.review.service.repository.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("reviewServiceImpl")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private RepoService repoService;

    @Autowired
    private PointInfoQueue<PointAudit> pointInfoQueue;

    @Override
    public ResponseData reviewHandle(EventData eventData) {
        ResponseData responseData;

        if (eventData.getAction().toLowerCase().equals(EventActionEnum.ADD_EVENT_ACTION.get())) {
            if (!eventData.existContentOrPhoto()) {
                responseData = ResponseMsgEnum.RES_BAD_REQ_CONTENT_PHOTO.get();
            } else {
                responseData = addReview(eventData);
            }
        } else if (eventData.getAction().toLowerCase().equals(EventActionEnum.MODIFY_EVENT_ACTION.get())) {
            if (!eventData.existContentOrPhoto()) {
                responseData = ResponseMsgEnum.RES_BAD_REQ_CONTENT_PHOTO.get();
            } else {
                responseData = modifyReview(eventData);
            }
        } else if (eventData.getAction().toLowerCase().equals(EventActionEnum.DELETE_EVENT_ACTION.get())) {
            responseData = deleteReview(eventData);
        } else {
            responseData = ResponseMsgEnum.RES_BAD_REQ_ACTION_TYPE.get();
        }
        return responseData;
    }

    @Override
    public ResponseData addReview(EventData eventData) {

        ResponseData responseData;
        if (repoService.existReview(eventData.getReviewId())) {
            responseData = ResponseMsgEnum.RES_BAD_REQ_EXIST_REVIEW_ID.get();
        } else {
            Review review = checkUserAndPlace(eventData.getUserId(), eventData.getPlaceId());
            if (review == null) {
                responseData = ResponseMsgEnum.RES_BAD_REQ_ALREADY_REGI_REVIEW.get();
            } else {
                try {
                    responseData = createReview(eventData, review);
                } catch (Exception e) {
                    responseData = ResponseMsgEnum.RES_SERVER_ERROR.get();
                }
            }
        }
        return responseData;
    }

    @Override
    public ResponseData modifyReview(EventData eventData) {
        ResponseData responseData;
        Review review = repoService.findReview(eventData.getReviewId(), eventData.getUserId(), eventData.getPlaceId());

        if (review == null) {
            responseData = ResponseMsgEnum.RES_BAD_REQ_NOT_EXIST_REVIEW_ID.get();
        } else {
            try {
                responseData = modifyReview(eventData, review);
            } catch (Exception e) {
                responseData = ResponseMsgEnum.RES_SERVER_ERROR.get();
            }
        }
        return responseData;
    }

    @Override
    public ResponseData deleteReview(EventData eventData) {
        ResponseData responseData;
        Review review = repoService.findReview(eventData.getReviewId(), eventData.getUserId(), eventData.getPlaceId());

        if (review == null) {
            responseData = ResponseMsgEnum.RES_BAD_REQ_NOT_EXIST_REVIEW_ID.get();
        } else {
            try {
                responseData = deleteReview(eventData, review);
            } catch (Exception e) {
                responseData = ResponseMsgEnum.RES_SERVER_ERROR.get();
            }
        }
        return responseData;
    }

    private ResponseData createReview(EventData eventData, Review review) throws Exception {
        ResponseData responseData;
        PointInfos pointInfos = new PointInfos();
        review.setReviewId(eventData.getReviewId());
        if (eventData.existContent()) {
            review.setReviewContent(eventData.getContent());
            pointInfos.checkPointEvent(PointEnum.POINT_FIRST_CONTENT, review);
        }

        if (eventData.existPhoto()) {
            boolean isAddPhoto = false;
            ReviewPhoto reviewPhoto;
            for (String photoId : eventData.getAttachedPhotoIds()) {
                if (Optional.ofNullable(photoId).filter(v -> v.length() > 0).isPresent()) {
                    isAddPhoto = true;
                    reviewPhoto = new ReviewPhoto(review, photoId);
                    review.addReviewPhoto(reviewPhoto);
                }
            }
            if (isAddPhoto) {
                pointInfos.checkPointEvent(PointEnum.POINT_FIRST_PHOTO, review);
            }
        }

        if (!repoService.existReviewOfPlace(review.getPlace())) {
            pointInfos.checkPointEvent(PointEnum.POINT_FIRST_RIVIEW, review);
        }

        repoService.saveRevieAndPhoto(review, true);
        responseData = ResponseMsgEnum.RES_CREATE_SUCC.get();
        pointInfoQueue.inQ(pointInfos.getPointAuditList());
        return responseData;
    }

    private ResponseData modifyReview(EventData eventData, Review review) throws Exception {
        ResponseData responseData;
        PointInfos pointInfos = new PointInfos();
        boolean isAddPhoto = false;

        if (eventData.existContent()) {
            review.setReviewContent(eventData.getContent());
            if (!review.existContent()) {
                pointInfos.checkPointEvent(PointEnum.POINT_FIRST_CONTENT, review);
            }
        }

        if (eventData.existPhoto()) {
            List<ReviewPhoto> reviewPhotoList = review.findExReviewPhoto(eventData.getAttachedPhotoIds());
            isAddPhoto = (reviewPhotoList.size() > 0 ? true : false);
            review.setReviewPhotoList(reviewPhotoList);
            if (isAddPhoto == true && review.existPhoto() == false) {
                pointInfos.checkPointEvent(PointEnum.POINT_FIRST_PHOTO, review);
            }
        }

        repoService.updateReviewAndPhoto(review, isAddPhoto);
        responseData = ResponseMsgEnum.RES_MODIFY_SUCC.get();
        pointInfoQueue.inQ(pointInfos.getPointAuditList());
        return responseData;
    }

    private ResponseData deleteReview(EventData eventData, Review review) throws Exception {
        ResponseData responseData;
        PointInfos pointInfos = new PointInfos();

        if (!eventData.existContentOrPhoto()) {
            if (review.existPhoto()) {
                pointInfos.checkPointEvent(PointEnum.POINT_DELETE_PHOTO, review);
            }
            if (review.existContent()) {
                pointInfos.checkPointEvent(PointEnum.POINT_DELETE_CONTENT, review);
            }
            repoService.deleteReviewAndPhoto(review);
        } else {
            List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

            if (review.equalsContentId(eventData.getContent())) {
                review.setReviewContent(null);
                pointInfos.checkPointEvent(PointEnum.POINT_DELETE_CONTENT, review);
            }

            if (eventData.existPhoto() && review.existPhoto()) {
                reviewPhotoList = review.findReviewPhoto(eventData.getAttachedPhotoIds());
                if (review.getReviewPhotoList().size() == reviewPhotoList.size()) {
                    pointInfos.checkPointEvent(PointEnum.POINT_DELETE_PHOTO, review);
                }
            }

            if (review.getReviewContent() == null && review.getReviewPhotoList().size() == reviewPhotoList.size()) {
                repoService.deleteReviewAndPhoto(review);
            } else {
                review.setReviewPhotoList(reviewPhotoList);
                repoService.updateReviewAndDeletePhoto(review, (reviewPhotoList.size() > 0 ? true : false));
            }
        }

        responseData = ResponseMsgEnum.RES_DELETE_SUCC.get();
        pointInfoQueue.inQ(pointInfos.getPointAuditList());
        return responseData;
    }

    private Review checkUserAndPlace(String userId, String placeId) {

        Review review = null;
        User user = repoService.findUser(userId, true);
        Place place = repoService.findPlace(placeId, true);
        if (user != null && place != null) {
            if (!repoService.existReview(user, place)) {
                review = new Review(user, place);
            }
        }
        return review;
    }
}
