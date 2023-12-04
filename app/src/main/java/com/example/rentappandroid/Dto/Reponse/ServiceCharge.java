package com.example.rentappandroid.Dto.Reponse;

public class ServiceCharge {
    private ServireChareReponse serviceChargeId;
    private int price;
    private String _id;

    public ServiceCharge() {
    }

    public ServireChareReponse getServiceChargeId() {
        return serviceChargeId;
    }

    public void setServiceChargeId(ServireChareReponse serviceChargeId) {
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

    public ServiceCharge(ServireChareReponse serviceChargeId, int price, String _id) {
        this.serviceChargeId = serviceChargeId;
        this.price = price;
        this._id = _id;
    }
}
