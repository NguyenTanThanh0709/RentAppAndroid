package com.example.rentappandroid.Dto.Request.Schema;

public class CommentReviewData {

    private String tenantId;

    private String roomingHouseId;

    private String content;

    private int rating;

    public CommentReviewData(String tenantId, String roomingHouseId, String content, int rating) {
        this.tenantId = tenantId;
        this.roomingHouseId = roomingHouseId;
        this.content = content;
        this.rating = rating;
    }

    public CommentReviewData() {
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getRoomingHouseId() {
        return roomingHouseId;
    }

    public void setRoomingHouseId(String roomingHouseId) {
        this.roomingHouseId = roomingHouseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
