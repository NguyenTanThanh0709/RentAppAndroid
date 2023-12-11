package com.example.rentappandroid.Dto.Reponse;

import com.example.rentappandroid.Dto.Request.Schema.Address;

import java.io.Serializable;
import java.util.List;

public class RoomingHouseComplex implements Serializable {
    private String _id;
    private String RoomingHouseComplex_name;
    private List<String> image_url;
    private List<RoomReponseComplex> listroom;
    private Owner owner;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<RoomReponseComplex> getListroom() {
        return listroom;
    }

    public void setListroom(List<RoomReponseComplex> listroom) {
        this.listroom = listroom;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<AreaInformation> getAreaInformation() {
        return areaInformation;
    }

    public void setAreaInformation(List<AreaInformation> areaInformation) {
        this.areaInformation = areaInformation;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public RoomingHouseComplex() {
    }

    private Address address;

    public RoomingHouseComplex(String _id, String roomingHouseComplex_name, List<String> image_url, List<RoomReponseComplex> listroom, Owner owner, Address address, List<AreaInformation> areaInformation, int __v) {
        this._id = _id;
        RoomingHouseComplex_name = roomingHouseComplex_name;
        this.image_url = image_url;
        this.listroom = listroom;
        this.owner = owner;
        this.address = address;
        this.areaInformation = areaInformation;
        this.__v = __v;
    }

    private List<AreaInformation> areaInformation;

    private int __v;

}
