package com.example.rentappandroid.Model;

public class LoaiNha {
    private String _id;
    private String typehouse_name;

    public LoaiNha(String _id, String typehouse_name) {
        this._id = _id;
        this.typehouse_name = typehouse_name;
    }

    public LoaiNha() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTypehouse_name() {
        return typehouse_name;
    }

    public void setTypehouse_name(String typehouse_name) {
        this.typehouse_name = typehouse_name;
    }
}
