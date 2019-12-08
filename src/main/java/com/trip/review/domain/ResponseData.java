package com.trip.review.domain;

import org.springframework.http.HttpStatus;

public class ResponseData {
    private String responsMsg;
    private HttpStatus httpStatus;

    public ResponseData() {
        this.responsMsg = "200 OK";
        this.httpStatus = HttpStatus.OK;
    }

    public ResponseData(String msg, HttpStatus httpStatus) {
        this.responsMsg = msg;
        this.httpStatus = httpStatus;
    }

    public String getResponsMsg() {
        return responsMsg;
    }

    public void setResponsMsg(String responsMsg) {
        this.responsMsg = responsMsg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
