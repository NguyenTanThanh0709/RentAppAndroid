package com.example.rentappandroid.Dto.Request.Add;

import com.example.rentappandroid.Dto.Request.Schema.Address;

import java.util.List;

public class RoomingHouseRequest {
    private String title;
    private String description;
    private int price;
    private int square_feet;
    private String status;
    private String roominghousecomplex;
    private String available_dates;
    private int peopeleNumber;
    private String owner;
    private String typehouse;
    private List<String> amenities;
    private Address address;
    private List<String> image_url;
    private List<String> rules;
    private List<ServiceChargeRE> serviceCharge;
    private List<AreaInformationRequest> areaInformation;


    public RoomingHouseRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getRoominghousecomplex() {
        return roominghousecomplex;
    }

    public void setRoominghousecomplex(String roominghousecomplex) {
        this.roominghousecomplex = roominghousecomplex;
    }

    public String getAvailable_dates() {
        return available_dates;
    }

    public void setAvailable_dates(String available_dates) {
        this.available_dates = available_dates;
    }

    public int getPeopeleNumber() {
        return peopeleNumber;
    }

    public void setPeopeleNumber(int peopeleNumber) {
        this.peopeleNumber = peopeleNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getImage_url() {
        return image_url;
    }

    public void setImage_url(List<String> image_url) {
        this.image_url = image_url;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public List<ServiceChargeRE> getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(List<ServiceChargeRE> serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public List<AreaInformationRequest> getAreaInformation() {
        return areaInformation;
    }

    public void setAreaInformation(List<AreaInformationRequest> areaInformation) {
        this.areaInformation = areaInformation;
    }

    public RoomingHouseRequest(String title, String description, int price, int square_feet, String status, String roominghousecomplex, String available_dates, int peopeleNumber, String owner, String typehouse, List<String> amenities, Address address, List<String> image_url, List<String> rules, List<ServiceChargeRE> serviceCharge, List<AreaInformationRequest> areaInformation) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.square_feet = square_feet;
        this.status = status;
        this.roominghousecomplex = roominghousecomplex;
        this.available_dates = available_dates;
        this.peopeleNumber = peopeleNumber;
        this.owner = owner;
        this.typehouse = typehouse;
        this.amenities = amenities;
        this.address = address;
        this.image_url = image_url;
        this.rules = rules;
        this.serviceCharge = serviceCharge;
        this.areaInformation = areaInformation;
    }
}
