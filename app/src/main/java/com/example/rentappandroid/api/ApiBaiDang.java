package com.example.rentappandroid.api;

import com.example.rentappandroid.Model.BaiViet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiBaiDang {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();

    ApiBaiDang apiBaiDang = new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiBaiDang.class);

    @GET("baiviet/all")
    Call<List<BaiViet>> getallBaiDang(@Header("Authorization") String token);

    @GET("baiviet/roominghouse/{id}")
    Call<List<BaiViet>> getallBaiDangByIdRoom(@Path("id") String id, @Header("Authorization") String token);

    @GET("baiviet/user/{id}")
    Call<List<BaiViet>> getallBaiDangByOwner(@Path("id") String id, @Header("Authorization") String token);

    @GET("baiviet/{id}")
    Call<BaiViet> getallBaiDangByid(@Path("id") String id, @Header("Authorization") String token);
}
