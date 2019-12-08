package com.trip.review.service.repository;

import com.trip.review.entity.*;
import com.trip.review.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("repoServiceImpl")
public class RepoServiceImpl implements RepoService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PlaceRepo placeRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    ReviewPhotoRepo reviewPhotoRepo;
    @Autowired
    PointAuditRepo pointAuditRepo;

    @Override
    public User findUser(String userId, boolean createFlag) {
        List<User> userList = userRepo.findByUserId(userId);

        if (!Optional.ofNullable(userList).filter(v -> v.size() > 0).isPresent()) {
            if (createFlag) {
                User user = new User(userId, 0);
                userRepo.save(user);
                System.out.println(String.format("user[%s] 를 생성 했습니다. 포인트:%d,시퀀스:%d", user.getUserId(), user.getUserPoint(), user.getUserSeq()));
                return user;
            } else {
                System.out.println(String.format("user[%s] 가 존재 하지 않습니다.", userId));
                return null;
            }
        } else {
            System.out.println(String.format("user[%s] 가 존재 합니다. 포인트:%d,시퀀스:%d", userList.get(0).getUserId(), userList.get(0).getUserPoint(), userList.get(0).getUserSeq()));
            return userList.get(0);
        }
    }

    @Override
    public Place findPlace(String placeId, boolean createFlag) {
        List<Place> placeList = placeRepo.findByPlaceId(placeId);

        if (!Optional.ofNullable(placeList).filter(v -> v.size() > 0).isPresent()) {
            if (createFlag) {
                Place place = new Place(placeId);
                placeRepo.save(place);
                System.out.println(String.format("place[%s] 를 생성 했습니다. 시퀀스:%d", place.getPlaceId(), place.getPlaceSeq()));
                return place;
            } else {
                System.out.println(String.format("place[%s] 가 존재 하지 않습니다.", placeId));
                return null;
            }
        } else {
            System.out.println(String.format("place[%s] 가 존재 합니다. 시퀀스:%d", placeList.get(0).getPlaceId(), placeList.get(0).getPlaceSeq()));
            return placeList.get(0);
        }
    }

    @Override
    public boolean existReview(User user, Place place) {
        List<Review> reviewList = reviewRepo.findByUserSeqAndPlaceSeq(user.getUserSeq(), place.getPlaceSeq());
        if (Optional.ofNullable(reviewList).filter(v -> v.size() > 0).isPresent()) {
            System.out.println(String.format("user[%s] 가 place[%s]에 생성한 리뷰가 존재 합니다.", user.getUserSeq(), place.getPlaceSeq()));
            for (Review agoReview : reviewList) {
                System.out.println(String.format("이미 존재 하는 리뷰:[%s]", agoReview.getReviewId()));
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existReview(String reviewId) {
        List<Review> reviewList = reviewRepo.findByReviewId(reviewId);
        if (Optional.ofNullable(reviewList).filter(v -> v.size() > 0).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review findReview(String reviewId, String userId, String placeId) {
        List<Review> reviewList = reviewRepo.findByReviewId(reviewId);
        if (Optional.ofNullable(reviewList).filter(v -> v.size() > 0)
                .filter(v -> v.get(0).getUser().getUserId().equals(userId))
                .filter(v -> v.get(0).getPlace().getPlaceId().equals(placeId))
                .isPresent()) {
            return reviewList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void saveRevieAndPhoto(Review review, boolean existPhoto) {
        reviewRepo.save(review);
        if (review.getReviewPhotoList() != null && existPhoto) {
            addPhoto(review);
        }
    }

    @Override
    public boolean existReviewOfPlace(Place place) {
        List<Place> placeList = placeRepo.findByPlaceId(place.getPlaceId());
        if (Optional.ofNullable(placeList)
                .filter(v -> v.size() > 0)
                .filter(v -> v.get(0).getReviewList() != null)
                .filter(v -> v.get(0).getReviewList().size() == 0)
                .isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void updateReviewAndPhoto(Review review, boolean existPhoto) {
        reviewRepo.updateReview(review.getReviewContent(), review.getReviewSeq());
        if (review.getReviewPhotoList() != null && existPhoto) {
            addPhoto(review);
        }
    }

    @Override
    public void deleteReviewAndPhoto(Review review) {
        if (review.getReviewPhotoList() != null) {
            deletePhoto(review);
        }
        reviewRepo.delete(review);
    }

    @Override
    public void updateReviewAndDeletePhoto(Review review, boolean existPhoto) {
        if (review.getReviewPhotoList() != null && existPhoto) {
            deletePhoto(review);
        }
        reviewRepo.updateReview(review.getReviewContent(), review.getReviewSeq());
    }

    @Override
    public void savePointAudit(PointAudit pointAudit) {
        pointAuditRepo.save(pointAudit);
    }

    @Override
    public List<PointAudit> findPointAudits(int userSeq) {
        return pointAuditRepo.findByUserSeq(userSeq);
    }

    private void deletePhoto(Review review) {
        for (ReviewPhoto reviewPhoto : review.getReviewPhotoList()) {
            reviewPhotoRepo.delete(reviewPhoto);
            System.out.println(String.format("Review[%s] User[%s] Place[%s] Photo[%s] 가 삭제 됐습니다.",
                    review.getReviewId(), review.getUser().getUserId(), review.getPlace().getPlaceId(), reviewPhoto.getPhotoId()));
        }
    }

    private void addPhoto(Review review) {
        for (ReviewPhoto reviewPhoto : review.getReviewPhotoList()) {
            reviewPhotoRepo.save(reviewPhoto);
            System.out.println(String.format("Review[%s] User[%s] Place[%s] Photo[%s] 가 생성 됐습니다.",
                    review.getReviewId(), review.getUser().getUserId(), review.getPlace().getPlaceId(), reviewPhoto.getPhotoId()));
        }
    }
}
