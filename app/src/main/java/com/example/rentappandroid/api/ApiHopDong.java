package com.example.rentappandroid.api;

import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.BaiViet;
import com.example.rentappandroid.Model.Leasecontracts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiHopDong {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();

    ApiHopDong apiHopDong = new Retrofit.Builder()
            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiHopDong.class);


    @GET("leasecontract/roominghouse/{id}")
    Call<List<Leasecontracts>> getallleasecontractByIdRoom(@Path("id") String id, @Header("Authorization") String token);

    @GET("leasecontract/landlord/{id}")
    Call<List<Leasecontracts>> getallleasecontractByOwner(@Path("id") String id, @Header("Authorization") String token);

    @GET("leasecontract/tenant/{id}")
    Call<List<Leasecontracts>> getallleasecontractBytenant(@Path("id") String id, @Header("Authorization") String token);

    @GET("leasecontract/{id}")
    Call<Leasecontracts> getallleasecontractByid(@Path("id") String id, @Header("Authorization") String token);


}
