package com.example.rentappandroid.Dto.Request.Add;

import com.example.rentappandroid.Dto.Request.Schema.Address;

import java.util.List;

public class timNguoiOGhepRequest {
    private String title;
    private String user;
    private String description;
    private int peopeleNumber;
    private int price;
    private List<String> image_url;
    private int square_feet;
    private String status;
    private String day_up;
    private List<String> amenities;
    private Address address;
    private List<ServiceChargeRE> serviceCharge;

    public timNguoiOGhepRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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



    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ServiceChargeRE> getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(List<ServiceChargeRE> serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public timNguoiOGhepRequest(String title, String user, String description, int peopeleNumber, int price, List<String> image_url, int square_feet, String status, String day_up, List<String> amenities, Address address, List<ServiceChargeRE> serviceCharge) {
        this.title = title;
        this.user = user;
        this.description = description;
        this.peopeleNumber = peopeleNumber;
        this.price = price;
        this.image_url = image_url;
        this.square_feet = square_feet;
        this.status = status;
        this.day_up = day_up;
        this.amenities = amenities;
        this.address = address;
        this.serviceCharge = serviceCharge;
    }
}
