package com.example.rentappandroid.Dto.Request.Add;

import com.example.rentappandroid.Dto.Request.Schema.Address;

import java.util.List;

public class RoomingHouseComplexRequest {
    private String RoomingHouseComplex_name;
    private List<String> image_url;
    private String owner;
    private Address address;
    private List<AreaInformationRequest> areaInformation;

    public RoomingHouseComplexRequest() {
    }

    public String getRoomingHouseComplex_name() {
        return RoomingHouseComplex_name;
    }

    public void setRoomingHouseComplex_name(String roomingHouseComplex_name) {
        RoomingHouseComplex_name = roomingHouseComplex_name;
    }

    public List<String> getImage_url() {
        return image_url;
    }

    public void setImage_url(List<String> image_url) {
        this.image_url = image_url;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<AreaInformationRequest> getAreaInformation() {
        return areaInformation;
    }

    public void setAreaInformation(List<AreaInformationRequest> areaInformation) {
        this.areaInformation = areaInformation;
    }


    public RoomingHouseComplexRequest(String roomingHouseComplex_name, List<String> image_url, String owner, Address address, List<AreaInformationRequest> areaInformation) {
        RoomingHouseComplex_name = roomingHouseComplex_name;
        this.image_url = image_url;
        this.owner = owner;
        this.address = address;
        this.areaInformation = areaInformation;
    }
}
