package com.example.rentappandroid.Dto.Request.Add;

import com.google.gson.annotations.SerializedName;

public class PostRequest {
    private String user;

    private int deposit;

    private String day_up;

    private String room;

    private String description;

    private String status;

    public PostRequest() {
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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

    public PostRequest(String user, int deposit, String day_up, String room, String description, String status) {
        this.user = user;
        this.deposit = deposit;
        this.day_up = day_up;
        this.room = room;
        this.description = description;
        this.status = status;
    }
}
