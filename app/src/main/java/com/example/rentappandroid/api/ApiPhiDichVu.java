package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Model.LoaiNha;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiPhiDichVu {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiPhiDichVu apiPhiDichVu = new Retrofit.Builder()

            .baseUrl("http://192.168.1.8:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiPhiDichVu.class);

    @GET("servicecharege/list")
    Call<List<ServireChareReponse>> getListPhiDichVu(@Header("Authorization") String token);
}
