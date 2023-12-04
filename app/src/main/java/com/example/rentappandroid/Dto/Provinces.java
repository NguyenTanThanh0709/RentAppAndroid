package com.example.rentappandroid.Dto;

public class Provinces {
    private String name;
    private  int code;

    public Provinces(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public Provinces() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
