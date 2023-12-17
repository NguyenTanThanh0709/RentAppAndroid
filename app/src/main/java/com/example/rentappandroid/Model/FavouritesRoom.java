package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;

import java.util.List;

public class FavouritesRoom {
    private String _id;
    private Owner user;
    private List<BaiViet> post;

    public List<BaiViet> getPost() {
        return post;
    }

    public void setPost(List<BaiViet> post) {
        this.post = post;
    }

    public FavouritesRoom(String _id, Owner user, List<BaiViet> post, int __v) {
        this._id = _id;
        this.user = user;
        this.post = post;
        this.__v = __v;
    }

    private int __v;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Owner getUser() {
        return user;
    }

    public void setUser(Owner user) {
        this.user = user;
    }



    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public FavouritesRoom(String _id, Owner user, int __v) {
        this._id = _id;
        this.user = user;
        this.__v = __v;
    }

    public FavouritesRoom() {
    }
}
