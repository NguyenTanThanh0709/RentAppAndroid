package com.example.rentappandroid.Dto.Request.Schema;

public class ServiceCharge {
    private String serviceChargeId;
    private int price;

    public ServiceCharge() {
    }

    public ServiceCharge(String serviceChargeId, int price) {
        this.serviceChargeId = serviceChargeId;
        this.price = price;
    }

    public String getServiceChargeId() {
        return serviceChargeId;
    }

    public void setServiceChargeId(String serviceChargeId) {
        this.serviceChargeId = serviceChargeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
// Constructors, getters, setters
}
