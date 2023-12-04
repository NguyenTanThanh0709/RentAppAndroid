package com.example.rentappandroid.Dto.Reponse;

import com.example.rentappandroid.Dto.Request.Schema.Address;

import java.util.List;

public class RoomingHouseComplex {
    private String _id;
    private String RoomingHouseComplex_name;
    private List<String> image_url;
    private List<String> listroom;
    private String owner;
    private Address address;
    private List<AreaInformation> areaInformation;
    private int __v;
}
