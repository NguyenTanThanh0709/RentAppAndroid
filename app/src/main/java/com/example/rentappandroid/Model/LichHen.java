package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.Room;

public class LichHen {
    private String _id;
    private Owner tenant;
    private Room roomingHouse;
    private String appointment_date;
    private String appointment_time;
    private String UNCONFIRMED;
    private int __v;

    public LichHen() {
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

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getUNCONFIRMED() {
        return UNCONFIRMED;
    }

    public void setUNCONFIRMED(String UNCONFIRMED) {
        this.UNCONFIRMED = UNCONFIRMED;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public LichHen(String _id, Owner tenant, Room roomingHouse, String appointment_date, String appointment_time, String UNCONFIRMED, int __v) {
        this._id = _id;
        this.tenant = tenant;
        this.roomingHouse = roomingHouse;
        this.appointment_date = appointment_date;
        this.appointment_time = appointment_time;
        this.UNCONFIRMED = UNCONFIRMED;
        this.__v = __v;
    }
}
