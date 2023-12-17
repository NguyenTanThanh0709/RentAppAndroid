package com.example.rentappandroid.api;

import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.FavouritesRoom;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiFavouriteRoom {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiFavouriteRoom apiApiFavouriteRoom = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiFavouriteRoom.class);

    @GET("favoritiesroom/user/{userId}")
    Call<FavouritesRoom> getFavouritesRoomByUser(@Path("userId") String userId,@Header("Authorization") String token);

    @PUT("favoritiesroom/user/{userId}/add-room/{roomId}")
    Call<Void> addRoomToUserFavorites(@Path("userId") String userId, @Path("roomId") String roomId,@Header("Authorization") String token);

    @PUT("favoritiesroom/user/{userId}/remove-room/{roomId}")
    Call<Void> removeRoomFromUserFavorites(@Path("userId") String userId, @Path("roomId") String roomId,@Header("Authorization") String token);
}
