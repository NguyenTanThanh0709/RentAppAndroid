package com.example.rentappandroid.Model;

public class TienNghi {
    private String _id;
    private String amenity_name;
    private String amenity_img;
    private Boolean status;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAmenity_name() {
        return amenity_name;
    }

    public void setAmenity_name(String amenity_name) {
        this.amenity_name = amenity_name;
    }

    public String getAmenity_img() {
        return amenity_img;
    }

    public void setAmenity_img(String amenity_img) {
        this.amenity_img = amenity_img;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TienNghi() {
    }

    public TienNghi(String _id, String amenity_name, String amenity_img, Boolean status) {
        this._id = _id;
        this.amenity_name = amenity_name;
        this.amenity_img = amenity_img;
        this.status = status;
    }
}
