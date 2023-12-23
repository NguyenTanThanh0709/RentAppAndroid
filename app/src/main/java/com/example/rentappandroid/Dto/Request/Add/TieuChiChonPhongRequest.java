package com.example.rentappandroid.Dto.Request.Add;

import java.util.List;

public class TieuChiChonPhongRequest {
    private String tenant;

    private String location;
    private int maxPrice;
    private int peopleNumber;

    private String typehouse;  // Replace with the actual data type if not a string

    private List<String> amenities;

    public TieuChiChonPhongRequest() {
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
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

    public TieuChiChonPhongRequest(String tenant, String location, int maxPrice, int peopleNumber, String typehouse, List<String> amenities) {
        this.tenant = tenant;
        this.location = location;
        this.maxPrice = maxPrice;
        this.peopleNumber = peopleNumber;
        this.typehouse = typehouse;
        this.amenities = amenities;
    }
}
