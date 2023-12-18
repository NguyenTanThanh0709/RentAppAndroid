package com.example.rentappandroid.Dto;

public class Ward {
    private String name;
    private  int code;
    private  int district_code;

    @Override
    public String toString() {
        return name;
    }

    public Ward(String name, int code, int district_code) {
        this.name = name;
        this.code = code;
        this.district_code = district_code;
    }

    public Ward() {
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

    public int getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(int district_code) {
        this.district_code = district_code;
    }
}
