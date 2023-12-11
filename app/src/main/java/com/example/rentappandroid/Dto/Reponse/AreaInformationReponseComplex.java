package com.example.rentappandroid.Dto.Reponse;

public class AreaInformationReponseComplex {
    private String areaInformationID;
    private double distance;

    public AreaInformationReponseComplex() {
    }

    public String getAreaInformationID() {
        return areaInformationID;
    }

    public void setAreaInformationID(String areaInformationID) {
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

    public AreaInformationReponseComplex(String areaInformationID, double distance, String description, String _id) {
        this.areaInformationID = areaInformationID;
        this.distance = distance;
        this.description = description;
        this._id = _id;
    }

    private String description;
    private String _id;
}
