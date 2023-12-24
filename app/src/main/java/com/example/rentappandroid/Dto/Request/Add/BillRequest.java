package com.example.rentappandroid.Dto.Request.Add;

public class BillRequest {
    private int amount;
    private String payment_date;
    private String description;
    private String leaseContract;
    private Boolean status;

    public BillRequest() {
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

    public String getLeaseContract() {
        return leaseContract;
    }

    public void setLeaseContract(String leaseContract) {
        this.leaseContract = leaseContract;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BillRequest(int amount, String payment_date, String description, String leaseContract, Boolean status) {
        this.amount = amount;
        this.payment_date = payment_date;
        this.description = description;
        this.leaseContract = leaseContract;
        this.status = status;
    }
}
