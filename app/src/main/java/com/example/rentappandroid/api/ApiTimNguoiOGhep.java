package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Request.Add.timNguoiOGhepRequest;
import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.TimNguoiOGhep;
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

public interface ApiTimNguoiOGhep {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiTimNguoiOGhep apiApiTimNguoiOGhep = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiTimNguoiOGhep.class);
    @POST("timnguoioghep/timNguoiOGheps")
    Call<Void> add(@Body timNguoiOGhepRequest timNguoiOGhep, @Header("Authorization") String token);

    // Update an existing TimNguoiOGhep by ID
    @PUT("timnguoioghep/timNguoiOGheps/{id}")
    Call<Void> put(@Path("id") String id, @Body timNguoiOGhepRequest timNguoiOGhep, @Header("Authorization") String token);

    // Delete a TimNguoiOGhep by ID
    @DELETE("timnguoioghep/timNguoiOGheps/{id}")
    Call<Void> deleteTimNguoiOGhep(@Path("id") String id, @Header("Authorization") String token);

    // Get all TimNguoiOGheps
    @GET("timnguoioghep/timNguoiOGheps")
    Call<List<TimNguoiOGhep>> getAllTimNguoiOGheps(@Header("Authorization") String token);

    // Get TimNguoiOGheps by user ID
    @GET("timnguoioghep/timNguoiOGheps/user/{userId}")
    Call<List<TimNguoiOGhep>> getTimNguoiOGhepsByUser(@Path("userId") String userId, @Header("Authorization") String token);

    // Get TimNguoiOGhep by ID
    @GET("timnguoioghep/timNguoiOGheps/id/{id}")
    Call<TimNguoiOGhep> getTimNguoiOGhepByID(@Path("id") String id, @Header("Authorization") String token);

    // Update status of a TimNguoiOGhep by ID
    @PUT("timnguoioghep/timNguoiOGheps/status/{id}")
    Call<Void> updateTimNguoiOGhepStatusById(@Path("id") String id, @Body UpdateStatusRequest updateStatusRequest, @Header("Authorization") String token);

}
