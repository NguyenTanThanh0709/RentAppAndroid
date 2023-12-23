package com.example.rentappandroid.Dto.Request.Add;

public class AreaInformationRequest {
    private String areaInformationID;
    private double distance = 0.0;
    private String description = "";

    public AreaInformationRequest() {
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

    public AreaInformationRequest(String areaInformationID, double distance, String description) {
        this.areaInformationID = areaInformationID;
        this.distance = distance;
        this.description = description;
    }
}
