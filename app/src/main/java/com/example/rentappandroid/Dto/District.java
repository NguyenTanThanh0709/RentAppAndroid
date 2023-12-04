package com.example.rentappandroid.Dto;

public class District {
    private String name;
    private  int code;
    private  int province_code;

    public District(String name, int code, int province_code) {
        this.name = name;
        this.code = code;
        this.province_code = province_code;
    }

    public District() {
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

    public int getProvince_code() {
        return province_code;
    }

    public void setProvince_code(int province_code) {
        this.province_code = province_code;
    }
}
