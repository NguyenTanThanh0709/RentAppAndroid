package com.example.rentappandroid.Dto.Request.Schema;

import java.io.Serializable;

public class Address implements Serializable {
    private String city;
    private String district;
    private String ward;
    private String street;
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Address(String city, String district, String ward, String street, String _id) {
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.street = street;
        this._id = _id;
    }

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Address(String city, String district, String ward, String street) {
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.street = street;
    }

    public String getFullAddress() {
        return this.street + " - " + this.ward + " - " + this.district + " - " + this.city;
    }
// Constructors, getters, setters
}
