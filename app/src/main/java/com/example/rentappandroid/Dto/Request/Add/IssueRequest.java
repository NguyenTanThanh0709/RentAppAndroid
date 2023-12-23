package com.example.rentappandroid.Dto.Request.Add;

public class IssueRequest {
    private String user;

    private String owner;

    private String room;

    private String description;

    private String date;

    private String status;

    public IssueRequest(String user, String owner, String room, String description, String date, String status) {
        this.user = user;
        this.owner = owner;
        this.room = room;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public IssueRequest() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
