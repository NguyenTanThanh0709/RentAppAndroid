package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Request.Add.TieuChiChonPhongRequest;
import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.searchcriterias;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiTieuChiChonPhong {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiTieuChiChonPhong apiApiTieuChiChonPhong = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiTieuChiChonPhong.class);

    @GET("searchcriteria/tenant/{userId}")
    Call<searchcriterias> getTieuChiPhong(@Path("userId") String userId, @Header("Authorization") String token);


    @GET("searchcriteria/getall")
    Call<List<searchcriterias>> getall(@Header("Authorization") String token);

    @PUT("searchcriteria/tenant/{userId}")
    Call<Void> updateTieuChi(@Path("userId") String userId, @Body TieuChiChonPhongRequest tieuChiChonPhongRequest, @Header("Authorization") String token);

}
