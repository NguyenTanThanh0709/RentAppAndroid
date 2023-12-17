package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.ServiceCharge;
import com.example.rentappandroid.Dto.Request.Schema.Address;

import java.util.List;

public class TimNguoiOGhep {
    private String _id;
    private String title;
    private Owner user;
    private String description;
    private int peopeleNumber;
    private int price;
    private List<String> image_url;
    private int square_feet;
    private String status;
    private String day_up;
    private Address address;

    public TimNguoiOGhep() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeopeleNumber() {
        return peopeleNumber;
    }

    public void setPeopeleNumber(int peopeleNumber) {
        this.peopeleNumber = peopeleNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getImage_url() {
        return image_url;
    }

    public void setImage_url(List<String> image_url) {
        this.image_url = image_url;
    }

    public int getSquare_feet() {
        return square_feet;
    }

    public void setSquare_feet(int square_feet) {
        this.square_feet = square_feet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDay_up() {
        return day_up;
    }

    public void setDay_up(String day_up) {
        this.day_up = day_up;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public List<ServiceCharge> getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(List<ServiceCharge> serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public TimNguoiOGhep(String _id, String title, Owner user, String description, int peopeleNumber, int price, List<String> image_url, int square_feet, String status, String day_up, Address address, LoaiNha typehouse, List<TienNghi> amenities, List<ServiceCharge> serviceCharge, int __v) {
        this._id = _id;
        this.title = title;
        this.user = user;
        this.description = description;
        this.peopeleNumber = peopeleNumber;
        this.price = price;
        this.image_url = image_url;
        this.square_feet = square_feet;
        this.status = status;
        this.day_up = day_up;
        this.address = address;
        this.typehouse = typehouse;
        this.amenities = amenities;
        this.serviceCharge = serviceCharge;
        this.__v = __v;
    }

    private LoaiNha typehouse;

    private List<TienNghi> amenities;
    private List<ServiceCharge> serviceCharge;
    private int __v;
}
