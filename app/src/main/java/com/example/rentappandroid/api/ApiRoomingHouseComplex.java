package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.RoomReponseComplex;
import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
import com.example.rentappandroid.Global.ValueGlobal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiRoomingHouseComplex {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiRoomingHouseComplex apiRoomingHouseComplex = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiRoomingHouseComplex.class);


    @GET("roomingHouseComplex/{id}/owner")
    Call<List<RoomingHouseComplex>> getListRoomingHouseComplexByOwer(@Path("id") String id, @Header("Authorization") String token);

    @GET("roomingHouseComplex/{id}")
    Call<RoomingHouseComplex> getListRoomingHouseComplexByID(@Path("id") String id, @Header("Authorization") String token);
}
