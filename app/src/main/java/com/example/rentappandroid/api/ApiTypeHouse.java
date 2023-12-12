package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.LoaiNha;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiTypeHouse {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiTypeHouse apiTypeHouse = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiTypeHouse.class);

    @GET("typehouse/list")
    Call<List<LoaiNha>> getListTypeHouse(@Header("Authorization") String token);
}
