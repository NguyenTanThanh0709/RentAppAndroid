package com.example.rentappandroid.Dto.Reponse;

import java.io.Serializable;

public class AreaInformation implements Serializable {
    private AreaInformationReponse areaInformationID;
    private double distance;
    private String description;
    private String _id;

    public AreaInformationReponse getAreaInformationID() {
        return areaInformationID;
    }

    public void setAreaInformationID(AreaInformationReponse areaInformationID) {
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

    public AreaInformation(AreaInformationReponse areaInformationID, double distance, String description, String _id) {
        this.areaInformationID = areaInformationID;
        this.distance = distance;
        this.description = description;
        this._id = _id;
    }
}
