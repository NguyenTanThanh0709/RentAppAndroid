package com.example.rentappandroid.Dto.Reponse;

import com.example.rentappandroid.Dto.Request.Schema.Address;
import com.example.rentappandroid.Model.LoaiNha;
import com.example.rentappandroid.Model.TienNghi;

import java.util.List;

public class Room {
    private String _id;
    private String title;
    private String description;
    private int peopeleNumber;
    private int price;
    private List<String> image_url;
    private int square_feet;
    private List<String> rules;
    private String status;
    private String available_dates;
    private String up_dates;


    private Owner owner;
    private Address address;
    private LoaiNha typehouse;

    private List<TienNghi> amenities;
    private String roominghousecomplex;

    private List<ServiceCharge> serviceCharge;
    private List<AreaInformation> areaInformation;
    private int __v;

    public Room() {
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

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailable_dates() {
        return available_dates;
    }

    public void setAvailable_dates(String available_dates) {
        this.available_dates = available_dates;
    }

    public String getUp_dates() {
        return up_dates;
    }

    public void setUp_dates(String up_dates) {
        this.up_dates = up_dates;
    }

    public String getRoominghousecomplex() {
        return roominghousecomplex;
    }

    public void setRoominghousecomplex(String roominghousecomplex) {
        this.roominghousecomplex = roominghousecomplex;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<TienNghi> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<TienNghi> amenities) {
        this.amenities = amenities;
    }

    public LoaiNha getTypehouse() {
        return typehouse;
    }

    public void setTypehouse(LoaiNha typehouse) {
        this.typehouse = typehouse;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Room(String _id, String title, String description, int peopeleNumber, int price, List<String> image_url, int square_feet, List<String> rules, String status, String available_dates, String up_dates, String roominghousecomplex, Owner owner, List<TienNghi> amenities, LoaiNha typehouse, Address address, List<ServiceCharge> serviceCharge, List<AreaInformation> areaInformation, int __v) {
        this._id = _id;
        this.title = title;
        this.description = description;
        this.peopeleNumber = peopeleNumber;
        this.price = price;
        this.image_url = image_url;
        this.square_feet = square_feet;
        this.rules = rules;
        this.status = status;
        this.available_dates = available_dates;
        this.up_dates = up_dates;
        this.roominghousecomplex = roominghousecomplex;
        this.owner = owner;
        this.amenities = amenities;
        this.typehouse = typehouse;
        this.address = address;
        this.serviceCharge = serviceCharge;
        this.areaInformation = areaInformation;
        this.__v = __v;
    }
}
