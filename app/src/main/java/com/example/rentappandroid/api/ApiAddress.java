package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.District;
import com.example.rentappandroid.Dto.Provinces;
import com.example.rentappandroid.Dto.Ward;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiAddress {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Set your desired connection timeout duration here
            .build();
    ApiAddress apiDriverTrip = new Retrofit.Builder()

            .baseUrl("https://provinces.open-api.vn/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiAddress.class);

    @GET("p/")
    Call<List<Provinces>> getListProvices();

    @GET("d/")
    Call<List<District>> getListDistricts();
    @GET("w/")
    Call<List<Ward>> getListWard();

}
