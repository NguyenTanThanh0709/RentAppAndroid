package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.Room;

public class Issue {
    private String _id;
    private Owner user;
    private Owner owner;
    private Room room;
    private String description;
    private String status;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private int __v;

    public Issue(String _id, Owner user, Owner owner, Room room, String description, String status, int __v, String date) {
        this._id = _id;
        this.user = user;
        this.owner = owner;
        this.room = room;
        this.description = description;
        this.status = status;
        this.__v = __v;
        this.date = date;
    }

    private String date;


    public Issue() {
    }

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Issue(String _id, Owner user, Owner owner, Room room, String description, String status, int __v) {
        this._id = _id;
        this.user = user;
        this.owner = owner;
        this.room = room;
        this.description = description;
        this.status = status;
        this.__v = __v;
    }
}
