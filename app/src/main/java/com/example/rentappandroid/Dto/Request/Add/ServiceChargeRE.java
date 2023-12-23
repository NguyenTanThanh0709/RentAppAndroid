package com.example.rentappandroid.Dto.Request.Add;

public class ServiceChargeRE {
    private String serviceChargeId;
    private Double price = 0.0;

    public ServiceChargeRE(String serviceChargeId, Double price) {
        this.serviceChargeId = serviceChargeId;
        this.price = price;
    }

    public ServiceChargeRE() {
    }

    public String getServiceChargeId() {
        return serviceChargeId;
    }

    public void setServiceChargeId(String serviceChargeId) {
        this.serviceChargeId = serviceChargeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
