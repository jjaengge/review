package com.trip.review.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "action",
        "reviewId",
        "content",
        "attachedPhotoIds",
        "userId",
        "placeId"
})
public class EventData implements Serializable {

    @JsonProperty("type")
    private String type;
    @JsonProperty("action")
    private String action;
    @JsonProperty("reviewId")
    private String reviewId;
    @JsonProperty("content")
    private String content;
    @JsonProperty("attachedPhotoIds")
    private List<String> attachedPhotoIds = null;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("placeId")
    private String placeId;
    private final static long serialVersionUID = -1178646292680854829L;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("reviewId")
    public String getReviewId() {
        return reviewId;
    }

    @JsonProperty("reviewId")
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("attachedPhotoIds")
    public List<String> getAttachedPhotoIds() {
        return attachedPhotoIds;
    }

    @JsonProperty("attachedPhotoIds")
    public void setAttachedPhotoIds(List<String> attachedPhotoIds) {
        this.attachedPhotoIds = attachedPhotoIds;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("placeId")
    public String getPlaceId() {
        return placeId;
    }

    @JsonProperty("placeId")
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @JsonIgnore
    public boolean isMalformed() {
        if (Optional.ofNullable(this)
                .filter(v -> v.getType() != null)
                .filter(v -> v.getAction() != null)
                .filter(v -> v.getReviewId() != null)
                .filter(v -> v.getUserId() != null)
                .filter(v -> v.getPlaceId() != null)
                .isPresent()) {
            System.out.println("req======> " + toString());
            return false;
        } else {
            System.out.println("wrong req======> " + toString());
            return true;
        }
    }

    @JsonIgnore
    public boolean existContentOrPhoto() {
        return (existContent() || existPhoto());
    }

    @JsonIgnore
    public boolean existContentAndPhoto() {
        return (existContent() && existPhoto());
    }

    @JsonIgnore
    public boolean existContent() {
        boolean existContent = false;

        if (Optional.ofNullable(content).filter(v -> v.length() > 0).isPresent()) {
            existContent = true;
        }

        return existContent;
    }

    @JsonIgnore
    public boolean existPhoto() {
        boolean existPhoto = false;

        if (Optional.ofNullable(attachedPhotoIds).filter(v -> v.size() > 0).isPresent()) {
            existPhoto = true;
        }

        return existPhoto;
    }

    @Override
    public String toString() {
        return "EventData{" +
                "type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", reviewId='" + reviewId + '\'' +
                ", content='" + content + '\'' +
                ", attachedPhotoIds=" + attachedPhotoIds +
                ", userId='" + userId + '\'' +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}
