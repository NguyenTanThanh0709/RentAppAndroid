package com.example.rentappandroid.api;

import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.Model.HoaDon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiHoadon {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();

    ApiHoadon apiHoadon = new Retrofit.Builder()
            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiHoadon.class);

    @GET("bill/list/{id}")
    Call<List<HoaDon>> getAllHoaDonByContract(@Path("id") String id, @Header("Authorization") String token);

    @GET("bill/one/{id}")
    Call<HoaDon> getHoaDonByContract(@Path("id") String id, @Header("Authorization") String token);

}
