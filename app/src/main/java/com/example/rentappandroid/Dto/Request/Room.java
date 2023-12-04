package com.example.rentappandroid.Dto.Request;

import com.example.rentappandroid.Dto.Request.Schema.Address;
import com.example.rentappandroid.Dto.Request.Schema.AreaInformation;
import com.example.rentappandroid.Dto.Request.Schema.ServiceCharge;

import java.util.List;

public class Room {
    private String title;
    private String description;
    private int price;
    private int squareFeet;
    private String status;
    private String roomingHouseComplex;
    private int peopleNumber;
    private String owner;
    private String typeHouse;
    private List<String> amenities;
    private Address address;
    private List<String> imageURL;
    private List<String> rules;
    private List<ServiceCharge> serviceCharge;
    private List<AreaInformation> areaInformation;

    public Room() {
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

    public int getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomingHouseComplex() {
        return roomingHouseComplex;
    }

    public void setRoomingHouseComplex(String roomingHouseComplex) {
        this.roomingHouseComplex = roomingHouseComplex;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTypeHouse() {
        return typeHouse;
    }

    public void setTypeHouse(String typeHouse) {
        this.typeHouse = typeHouse;
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

    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public List<ServiceCharge> getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(List<ServiceCharge> serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public List<AreaInformation> getAreaInformation() {
        return areaInformation;
    }

    public void setAreaInformation(List<AreaInformation> areaInformation) {
        this.areaInformation = areaInformation;
    }

    public Room(String title, String description, int price, int squareFeet, String status, String roomingHouseComplex, int peopleNumber, String owner, String typeHouse, List<String> amenities, Address address, List<String> imageURL, List<String> rules, List<ServiceCharge> serviceCharge, List<AreaInformation> areaInformation) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.squareFeet = squareFeet;
        this.status = status;
        this.roomingHouseComplex = roomingHouseComplex;
        this.peopleNumber = peopleNumber;
        this.owner = owner;
        this.typeHouse = typeHouse;
        this.amenities = amenities;
        this.address = address;
        this.imageURL = imageURL;
        this.rules = rules;
        this.serviceCharge = serviceCharge;
        this.areaInformation = areaInformation;
    }



    // Constructors, getters, setters
}
