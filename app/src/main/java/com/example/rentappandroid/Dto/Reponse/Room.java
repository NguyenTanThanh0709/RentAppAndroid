package com.example.rentappandroid.Dto.Reponse;

import com.example.rentappandroid.Dto.Request.Schema.Address;
import com.example.rentappandroid.Model.LoaiNha;

import java.util.List;

public class Room {
    private String _id;
    private String title;
    private String description;
    private int peopeleNumber;
    private int price;
    private List<String> image_url;
    private int square_feet;
    private List<String> rules;
    private String status;
    private String available_dates;
    private String up_dates;
    private RoomingHouseComplex roominghousecomplex;
    private Owner owner;
    private List<Amenity> amenities;
    private LoaiNha typehouse;
    private Address address;
    private List<ServiceCharge> serviceCharge;
    private List<AreaInformation> areaInformation;
    private int __v;
}
