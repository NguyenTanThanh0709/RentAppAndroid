package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.Room;

public class BaiViet {
    private String _id;
    private String user;
    private int deposit;
    private String day_up;
    private Room room;
    private String description;
    private String status;
    private int __v;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getDay_up() {
        return day_up;
    }

    public void setDay_up(String day_up) {
        this.day_up = day_up;
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

    public BaiViet() {
    }

    public BaiViet(String _id, String user, int deposit, String day_up, Room room, String description, String status, int __v) {
        this._id = _id;
        this.user = user;
        this.deposit = deposit;
        this.day_up = day_up;
        this.room = room;
        this.description = description;
        this.status = status;
        this.__v = __v;
    }
}
