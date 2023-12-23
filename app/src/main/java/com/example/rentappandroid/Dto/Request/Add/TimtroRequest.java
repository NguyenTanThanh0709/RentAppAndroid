package com.example.rentappandroid.Dto.Request.Add;

import java.util.List;

public class TimtroRequest {
    private String user;
    private String day_up;
    private String typehouse;
    private List<String> amenities;
    private int peopeleNumber;
    private String address;
    private int maxPrice;
    private String description;
    private String title;
    private String status;

    public TimtroRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDay_up() {
        return day_up;
    }

    public void setDay_up(String day_up) {
        this.day_up = day_up;
    }

    public String getTypehouse() {
        return typehouse;
    }

    public void setTypehouse(String typehouse) {
        this.typehouse = typehouse;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public int getPeopeleNumber() {
        return peopeleNumber;
    }

    public void setPeopeleNumber(int peopeleNumber) {
        this.peopeleNumber = peopeleNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TimtroRequest(String user, String day_up, String typehouse, List<String> amenities, int peopeleNumber, String address, int maxPrice, String description, String title, String status) {
        this.user = user;
        this.day_up = day_up;
        this.typehouse = typehouse;
        this.amenities = amenities;
        this.peopeleNumber = peopeleNumber;
        this.address = address;
        this.maxPrice = maxPrice;
        this.description = description;
        this.title = title;
        this.status = status;
    }
}
