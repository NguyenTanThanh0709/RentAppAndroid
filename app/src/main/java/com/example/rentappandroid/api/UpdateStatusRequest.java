package com.example.rentappandroid.api;

public class UpdateStatusRequest {
    private String newStatus;

    public UpdateStatusRequest() {
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public UpdateStatusRequest(String newStatus) {
        this.newStatus = newStatus;
    }
}
