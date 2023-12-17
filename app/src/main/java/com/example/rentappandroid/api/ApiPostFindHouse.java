package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.ServireChareReponse;
import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.FindRoomHouseResponse;
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
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiPostFindHouse {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiPostFindHouse apiApiPostFindHouse = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiPostFindHouse.class);

    @GET("/findhouse/findRoomHouses")
    Call<List<FindRoomHouseResponse>> getAllFindRoomHouses(@Header("Authorization") String token);

    @DELETE("/findhouse/findRoomHouses/{id}")
    Call<Void> deleteFindRoomHouse(@Path("id") String id, @Header("Authorization") String token);


    @PUT("/findhouse/findRoomHouses/status/{id}")
    Call<FindRoomHouseResponse> updateFindRoomHouseStatusById(@Path("id") String id, @Body UpdateStatusRequest updateStatusRequest, @Header("Authorization") String token);


    @GET("findhouse/findRoomHouses/id/{id}")
    Call<FindRoomHouseResponse> getFindRoomHousesByID(@Path("id") String id,@Header("Authorization") String token);

    @GET("findhouse/findRoomHouses/user/{userId}")
    Call<List<FindRoomHouseResponse>> getFindRoomHousesByUser(@Path("userId") String userId,@Header("Authorization") String token);

}


