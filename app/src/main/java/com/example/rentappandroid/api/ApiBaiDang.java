package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Request.Add.PostRequest;
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

public interface ApiBaiDang {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();

    ApiBaiDang apiBaiDang = new Retrofit.Builder()
            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiBaiDang.class);

    @GET("baiviet/all")
    Call<List<BaiViet>> getallBaiDang(@Header("Authorization") String token);

    @GET("baiviet/roominghouse/{id}")
    Call<List<BaiViet>> getallBaiDangByIdRoom(@Path("id") String id, @Header("Authorization") String token);

    @GET("baiviet/user/{id}")
    Call<List<BaiViet>> getallBaiDangByOwner(@Path("id") String id, @Header("Authorization") String token);

    @GET("baiviet/{id}")
    Call<BaiViet> getallBaiDangByid(@Path("id") String id, @Header("Authorization") String token);

    @PUT("baiviet/update/{id}")
    Call<Void> update(@Path("id") String id,@Body PostRequest postRequest, @Header("Authorization") String token);


    @DELETE("baiviet/delete/{id}")
    Call<Void> delete(@Path("id") String id, @Header("Authorization") String token);



    @POST("baiviet/create")
    Call<Void> Add(@Body PostRequest postRequest, @Header("Authorization") String token);
}
