package com.example.rentappandroid.Model;

public class HoaDon {
    private String _id;
    private int amount;
    private String payment_date;
    private String description;
    private Leasecontracts leaseContract;

    public HoaDon() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Leasecontracts getLeaseContract() {
        return leaseContract;
    }

    public void setLeaseContract(Leasecontracts leaseContract) {
        this.leaseContract = leaseContract;
    }

    public HoaDon(String _id, int amount, String payment_date, String description, Leasecontracts leaseContract) {
        this._id = _id;
        this.amount = amount;
        this.payment_date = payment_date;
        this.description = description;
        this.leaseContract = leaseContract;
    }
}
