package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;

public class Notification {
    private String _id;
    private String description;
    private Owner tenant;
    private Owner landlord;
    private String date;
    private String type;
    private String id_type;

    public Notification(String _id, String description, Owner tenant, Owner landlord, String date, String type, String id_type) {
        this._id = _id;
        this.description = description;
        this.tenant = tenant;
        this.landlord = landlord;
        this.date = date;
        this.type = type;
        this.id_type = id_type;
    }

    public Notification() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getTenant() {
        return tenant;
    }

    public void setTenant(Owner tenant) {
        this.tenant = tenant;
    }

    public Owner getLandlord() {
        return landlord;
    }

    public void setLandlord(Owner landlord) {
        this.landlord = landlord;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }
}
