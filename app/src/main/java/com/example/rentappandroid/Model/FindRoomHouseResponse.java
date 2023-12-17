package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;

import java.util.List;

public class FindRoomHouseResponse {
    private String _id;
    private Owner user;
    private String day_up;
    private LoaiNha typehouse;
    private List<TienNghi> amenities;
    private int peopeleNumber;
    private double maxPrice;
    private String description;
    private String title;
    private String address;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public String getDay_up() {
        return day_up;
    }

    public void setDay_up(String day_up) {
        this.day_up = day_up;
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

    public int getPeopeleNumber() {
        return peopeleNumber;
    }

    public void setPeopeleNumber(int peopeleNumber) {
        this.peopeleNumber = peopeleNumber;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public FindRoomHouseResponse() {
    }

    public FindRoomHouseResponse(String _id, Owner user, String day_up, LoaiNha typehouse, List<TienNghi> amenities, int peopeleNumber, double maxPrice, String description, String title, String address, String status, int __v) {
        this._id = _id;
        this.user = user;
        this.day_up = day_up;
        this.typehouse = typehouse;
        this.amenities = amenities;
        this.peopeleNumber = peopeleNumber;
        this.maxPrice = maxPrice;
        this.description = description;
        this.title = title;
        this.address = address;
        this.status = status;
        this.__v = __v;
    }

    private String status;
    private int __v;
}
