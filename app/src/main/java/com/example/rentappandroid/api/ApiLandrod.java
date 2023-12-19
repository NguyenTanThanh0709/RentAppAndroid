package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Dto.Request.Schema.Login;
import com.example.rentappandroid.Global.ValueGlobal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiLandrod {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiLandrod apiLandrod = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLandrod.class);

    @POST("landlord/login")
    Call<Owner> login(@Body Login login);

    @GET("landlord/getById/{id}")
    Call<Owner> getOne(@Path("id") String id, @Header("Authorization") String token);
}
