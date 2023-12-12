package com.example.rentappandroid.Model;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.Room;

import java.util.List;

public class Leasecontracts {
    private String _id;
    private Owner tenant;
    private Owner landlord;
    private Room roomingHouse;

    private String create_date;
    private String start_date;
    private String end_date;
    private String billing_start_date;
    private int rent_price;
    private int deposit;
    private String cccd_front;
    private String cccd_back;
    private int payment_term;
    private List<String> image_url;
    private boolean status;
    private boolean is_paid;
    private int __v;

    public Leasecontracts() {
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

    public Owner getLandlord() {
        return landlord;
    }

    public void setLandlord(Owner landlord) {
        this.landlord = landlord;
    }

    public Room getRoomingHouse() {
        return roomingHouse;
    }

    public void setRoomingHouse(Room roomingHouse) {
        this.roomingHouse = roomingHouse;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getBilling_start_date() {
        return billing_start_date;
    }

    public void setBilling_start_date(String billing_start_date) {
        this.billing_start_date = billing_start_date;
    }

    public int getRent_price() {
        return rent_price;
    }

    public void setRent_price(int rent_price) {
        this.rent_price = rent_price;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getCccd_front() {
        return cccd_front;
    }

    public void setCccd_front(String cccd_front) {
        this.cccd_front = cccd_front;
    }

    public String getCccd_back() {
        return cccd_back;
    }

    public void setCccd_back(String cccd_back) {
        this.cccd_back = cccd_back;
    }

    public int getPayment_term() {
        return payment_term;
    }

    public void setPayment_term(int payment_term) {
        this.payment_term = payment_term;
    }

    public List<String> getImage_url() {
        return image_url;
    }

    public void setImage_url(List<String> image_url) {
        this.image_url = image_url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Leasecontracts(String _id, Owner tenant, Owner landlord, Room roomingHouse, String create_date, String start_date, String end_date, String billing_start_date, int rent_price, int deposit, String cccd_front, String cccd_back, int payment_term, List<String> image_url, boolean status, boolean is_paid, int __v) {
        this._id = _id;
        this.tenant = tenant;
        this.landlord = landlord;
        this.roomingHouse = roomingHouse;
        this.create_date = create_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.billing_start_date = billing_start_date;
        this.rent_price = rent_price;
        this.deposit = deposit;
        this.cccd_front = cccd_front;
        this.cccd_back = cccd_back;
        this.payment_term = payment_term;
        this.image_url = image_url;
        this.status = status;
        this.is_paid = is_paid;
        this.__v = __v;
    }
}
