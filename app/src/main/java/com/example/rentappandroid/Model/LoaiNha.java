package com.example.rentappandroid.Model;

public class LoaiNha {
    private String _id;
    private String typehouse_name;
    private int __v;

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public LoaiNha(String _id, String typehouse_name, int __v) {
        this._id = _id;
        this.typehouse_name = typehouse_name;
        this.__v = __v;
    }

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
