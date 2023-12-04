package com.example.rentappandroid.Dto.Request.Schema;

public class AreaInformation {
    private String areaInformationID;
    private double distance;
    private String description;

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

    public AreaInformation() {
    }

    public AreaInformation(String areaInformationID, double distance, String description) {
        this.areaInformationID = areaInformationID;
        this.distance = distance;
        this.description = description;
    }
}
