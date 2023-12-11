package com.example.rentappandroid.Dto.Request.Schema;

import com.example.rentappandroid.Dto.Reponse.AreaInformationID;

public class AreaInformation {
    private AreaInformationID areaInformationID;
    private double distance;
    private String description;
    private String _id;

    public AreaInformationID getAreaInformationID() {
        return areaInformationID;
    }

    public void setAreaInformationID(AreaInformationID areaInformationID) {
        this.areaInformationID = areaInformationID;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public AreaInformation() {
    }

    public AreaInformation(AreaInformationID areaInformationID, double distance, String description, String _id) {
        this.areaInformationID = areaInformationID;
        this.distance = distance;
        this.description = description;
        this._id = _id;
    }
}
