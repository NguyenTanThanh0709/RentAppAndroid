package com.example.rentappandroid.Dto.Request.Schema;

public class AppointmentRequest {
    private String tenant;

    private String roomingHouse;

    private String appointment_description;

    private String appointment_time;

    private String status;

    // Constructors, getters, and setters

    public AppointmentRequest() {
        // Default constructor
    }

    public AppointmentRequest(String tenant, String roomingHouse, String appointmentDescription, String appointmentTime, String status) {
        this.tenant = tenant;
        this.roomingHouse = roomingHouse;
        this.appointment_description = appointmentDescription;
        this.appointment_time = appointmentTime;
        this.status = status;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getRoomingHouse() {
        return roomingHouse;
    }

    public void setRoomingHouse(String roomingHouse) {
        this.roomingHouse = roomingHouse;
    }

    public String getAppointmentDescription() {
        return appointment_description;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointment_description = appointmentDescription;
    }

    public String getAppointmentTime() {
        return appointment_time;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointment_time = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
