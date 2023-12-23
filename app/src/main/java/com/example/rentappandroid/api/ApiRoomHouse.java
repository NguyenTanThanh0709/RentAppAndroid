package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Reponse.Room;
import com.example.rentappandroid.Dto.Request.Add.RoomingHouseRequest;
import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.BaiViet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiRoomHouse {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiRoomHouse apiRoom = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiRoomHouse.class);

    @GET("roominghouse/getListByOwnerId/{id}")
    Call<List<Room>> getListRoomByOwner(@Path("id") String id, @Header("Authorization") String token);


    @GET("baiviet/user/{id}")
    Call<List<BaiViet>> getListBaivietyOwner(@Path("id") String id, @Header("Authorization") String token);

    @GET("roominghouse/getPhongTro/{id}")
    Call<Room> getListRoomByID(@Path("id") String id, @Header("Authorization") String token);

    @POST("roominghouse/add")
    Call<Void> add(@Body RoomingHouseRequest roomingHouseRequest, @Header("Authorization") String token);

    @PUT("roominghouse/updatePhongTro/{id}")
    Call<Void> Put(@Path("id") String id,@Body RoomingHouseRequest roomingHouseRequest, @Header("Authorization") String token);

    @DELETE("roominghouse/deletePhongTro/{id}")
    Call<Void> Delete(@Path("id") String id, @Header("Authorization") String token);



}
