package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.Room;

public class CommentReview {
    private String _id;
    private Owner tenant;
    private Room roomingHouse;
    private String comment_date;
    private String content;
    private int rating;
    private int __v;

    public CommentReview() {
    }

    public CommentReview(String _id, Owner tenant, Room roomingHouse, String comment_date, String content, int rating, int __v) {
        this._id = _id;
        this.tenant = tenant;
        this.roomingHouse = roomingHouse;
        this.comment_date = comment_date;
        this.content = content;
        this.rating = rating;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Owner getTenant() {
        return tenant;
    }

    public void setTenant(Owner tenant) {
        this.tenant = tenant;
    }

    public Room getRoomingHouse() {
        return roomingHouse;
    }

    public void setRoomingHouse(Room roomingHouse) {
        this.roomingHouse = roomingHouse;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
