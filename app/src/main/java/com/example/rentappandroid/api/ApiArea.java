package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Reponse.RoomingHouseComplex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiArea {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiArea apiArea = new Retrofit.Builder()

            .baseUrl("http://192.168.1.8:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiArea.class);

    @GET("areaInformation/list")
    Call<List<AreaInformationReponse>> getListArea(@Header("Authorization") String token);

}
