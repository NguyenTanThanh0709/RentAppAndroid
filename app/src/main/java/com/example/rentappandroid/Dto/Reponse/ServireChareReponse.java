package com.example.rentappandroid.Dto.Reponse;

public class ServireChareReponse {

    private String _id;
    private String servicecharge_name;
    private String servicecharge_img;
    private boolean status;
    private String phi ="0";

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    private int __v;

    public ServireChareReponse(String _id, String servicecharge_name, String servicecharge_img, boolean status, int __v) {
        this._id = _id;
        this.servicecharge_name = servicecharge_name;
        this.servicecharge_img = servicecharge_img;
        this.status = status;
        this.__v = __v;
    }



    public String getPhi() {
        return phi;
    }

    public void setPhi(String phi) {
        this.phi = phi;
    }

    // Constructors
    public ServireChareReponse() {
    }

    public ServireChareReponse(String _id, String servicecharge_name, String servicecharge_img, boolean status) {
        this._id = _id;
        this.servicecharge_name = servicecharge_name;
        this.servicecharge_img = servicecharge_img;
        this.status = status;
        phi = "";
    }

    // Getters and Setters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getServicecharge_name() {
        return servicecharge_name;
    }

    public void setServicecharge_name(String servicecharge_name) {
        this.servicecharge_name = servicecharge_name;
    }

    public String getServicecharge_img() {
        return servicecharge_img;
    }

    public void setServicecharge_img(String servicecharge_img) {
        this.servicecharge_img = servicecharge_img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
