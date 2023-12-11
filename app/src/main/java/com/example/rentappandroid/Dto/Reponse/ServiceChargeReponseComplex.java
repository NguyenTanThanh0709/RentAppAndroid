package com.example.rentappandroid.Dto.Reponse;

public class ServiceChargeReponseComplex {
    private String serviceChargeId;
    private int price;
    private String _id;

    public ServiceChargeReponseComplex() {
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ServiceChargeReponseComplex(String serviceChargeId, int price, String _id) {
        this.serviceChargeId = serviceChargeId;
        this.price = price;
        this._id = _id;
    }
}
