package com.example.rentappandroid.Model;

import java.util.List;

public class searchcriterias {
    private String _id;

    private String tenant;
    private String location;
    private int maxPrice;
    private int peopleNumber;
    private LoaiNha typehouse;
    private List<TienNghi> amenities;
    private  int __v;

    public searchcriterias() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public LoaiNha getTypehouse() {
        return typehouse;
    }

    public void setTypehouse(LoaiNha typehouse) {
        this.typehouse = typehouse;
    }

    public List<TienNghi> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<TienNghi> amenities) {
        this.amenities = amenities;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public searchcriterias(String _id, String tenant, String location, int maxPrice, int peopleNumber, LoaiNha typehouse, List<TienNghi> amenities, int __v) {
        this._id = _id;
        this.tenant = tenant;
        this.location = location;
        this.maxPrice = maxPrice;
        this.peopleNumber = peopleNumber;
        this.typehouse = typehouse;
        this.amenities = amenities;
        this.__v = __v;
    }
}
