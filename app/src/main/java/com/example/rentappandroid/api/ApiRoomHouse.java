package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Reponse.Room;
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

public interface ApiRoomHouse {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiRoomHouse apiRoom = new Retrofit.Builder()

            .baseUrl("http://192.168.1.8:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiRoomHouse.class);

    @GET("roominghouse/getListByOwnerId/{id}")
    Call<List<Room>> getListRoomByOwner(@Path("id") String id, @Header("Authorization") String token);


    @GET("baiviet/user/{id}")
    Call<List<BaiViet>> getListBaivietyOwner(@Path("id") String id, @Header("Authorization") String token);

    @GET("roominghouse/getPhongTro/{id}")
    Call<Room> getListRoomByID(@Path("id") String id, @Header("Authorization") String token);



}
