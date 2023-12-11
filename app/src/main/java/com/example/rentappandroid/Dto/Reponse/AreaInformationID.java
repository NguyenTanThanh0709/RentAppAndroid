package com.example.rentappandroid.Dto.Reponse;

public class AreaInformationID {
    private String _id;
    private String areainformation_name;
    private String areainformation_img;
    private int __v;

    public AreaInformationID() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAreainformation_name() {
        return areainformation_name;
    }

    public void setAreainformation_name(String areainformation_name) {
        this.areainformation_name = areainformation_name;
    }

    public String getAreainformation_img() {
        return areainformation_img;
    }

    public void setAreainformation_img(String areainformation_img) {
        this.areainformation_img = areainformation_img;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public AreaInformationID(String _id, String areainformation_name, String areainformation_img, int __v) {
        this._id = _id;
        this.areainformation_name = areainformation_name;
        this.areainformation_img = areainformation_img;
        this.__v = __v;
    }
}
