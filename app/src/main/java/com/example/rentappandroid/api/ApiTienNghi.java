package com.example.rentappandroid.api;

import com.example.rentappandroid.Model.LoaiNha;
import com.example.rentappandroid.Model.TienNghi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiTienNghi {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiTienNghi apiTienNghi = new Retrofit.Builder()

            .baseUrl("http://192.168.1.8:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiTienNghi.class);

    @GET("amenities/list")
    Call<List<TienNghi>> getListTienNghi(@Header("Authorization") String token);
}
