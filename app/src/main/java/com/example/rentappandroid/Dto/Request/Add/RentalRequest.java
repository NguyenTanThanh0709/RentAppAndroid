package com.example.rentappandroid.Dto.Request.Add;

import java.util.List;

public class RentalRequest {
    private String tenant_phone;
    private String password;
    private String tenant;  // Replace with an actual Tenant ID
    private String landlord;  // Replace with an actual Landlord ID
    private String cccd_front;
    private String cccd_back;
    private String roomingHouse;  // Replace with an actual RoomingHouse ID
    private String create_date;
    private String start_date;
    private String end_date;
    private String billing_start_date;
    private int rent_price;
    private int deposit;
    private int payment_term;
    private List<String> image_url;
    private boolean status;
    private boolean is_paid;

    public RentalRequest() {
    }

    public String getTenant_phone() {
        return tenant_phone;
    }

    public void setTenant_phone(String tenant_phone) {
        this.tenant_phone = tenant_phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getLandlord() {
        return landlord;
    }

    public void setLandlord(String landlord) {
        this.landlord = landlord;
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

    public String getRoomingHouse() {
        return roomingHouse;
    }

    public void setRoomingHouse(String roomingHouse) {
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

    public RentalRequest(String tenant_phone, String password, String tenant, String landlord, String cccd_front, String cccd_back, String roomingHouse, String create_date, String start_date, String end_date, String billing_start_date, int rent_price, int deposit, int payment_term, List<String> image_url, boolean status, boolean is_paid) {
        this.tenant_phone = tenant_phone;
        this.password = password;
        this.tenant = tenant;
        this.landlord = landlord;
        this.cccd_front = cccd_front;
        this.cccd_back = cccd_back;
        this.roomingHouse = roomingHouse;
        this.create_date = create_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.billing_start_date = billing_start_date;
        this.rent_price = rent_price;
        this.deposit = deposit;
        this.payment_term = payment_term;
        this.image_url = image_url;
        this.status = status;
        this.is_paid = is_paid;
    }
// Getters and setters
}
