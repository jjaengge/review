package com.trip.review.enumeration;

import com.trip.review.domain.ResponseData;
import org.springframework.http.HttpStatus;

public enum ResponseMsgEnum {
    RES_BAD_REQ_CONTENT_PHOTO("리뷰를 등록 하려면 댓글 또는 사진이 필요합니다.", HttpStatus.BAD_REQUEST),
    RES_BAD_REQ_ACTION_TYPE("액션(action) 값을 확인 하시오.", HttpStatus.BAD_REQUEST),
    RES_BAD_REQ_TYPE("타입(type) 값을 확인 하시오.", HttpStatus.BAD_REQUEST),
    RES_BAD_REQ_EXIST_REVIEW_ID("리뷰 아이디가 이미 존재 합니다. 유니크한 값으로 다시 시도하시오.", HttpStatus.BAD_REQUEST),
    RES_BAD_REQ_ALREADY_REGI_REVIEW("이미 리뷰를 등록 했습니다. 댓글 수정,삭제 사진 수정,삭제 리뷰 전체 삭제가 가능 합니다.", HttpStatus.BAD_REQUEST),
    RES_BAD_REQ_NOT_EXIST_REVIEW_ID("리뷰 아이디가 존재 하지 않습니다.", HttpStatus.BAD_REQUEST),
    RES_BAD_REQ_MALFORMED_JSON("Json 데이터를 확인 하시오.", HttpStatus.BAD_REQUEST),

    RES_USER_NOT_FOUND("유저가 존재 하지 않습니다.", HttpStatus.NOT_FOUND),

    RES_SERVER_ERROR("리뷰 데이터 생성에서 오류가 발생 햇습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    RES_MODIFY_SUCC("리뷰 수정 성공.", HttpStatus.OK),
    RES_DELETE_SUCC("리뷰 삭제 성공.", HttpStatus.OK),
    RES_CREATE_SUCC("리뷰 등록 성공.", HttpStatus.CREATED);


    public final String msg;
    public final HttpStatus httpStatus;

    private ResponseMsgEnum(String msg, HttpStatus httpStatus) {
        this.msg = msg;
        this.httpStatus = httpStatus;
    }

    public ResponseData get() {
        return new ResponseData(this.msg, this.httpStatus);
    }
}
