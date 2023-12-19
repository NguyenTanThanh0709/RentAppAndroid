package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Request.Schema.CommentReviewData;
import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.CommentReview;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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

public interface ApiComment {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)  // Set your desired connection timeout duration here
            .build();
    ApiComment apiApiComment = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiComment.class);


    @POST("comment")
    Call<Void> addCommentReview(@Body CommentReviewData commentReviewData, @Header("Authorization") String token);

    // Update CommentReview by ID
    @PUT("comment/{id}")
    Call<Void> updateCommentReview(@Path("id") String id, @Body CommentReviewData commentReviewData, @Header("Authorization") String token);

    // Delete CommentReview by ID
    @DELETE("comment/{id}")
    Call<Void> deleteCommentReview(@Path("id") String id, @Header("Authorization") String token);

    // Get comments by RoomingHouse ID
    @GET("comment/roomingHouse/{roomingHouseId}")
    Call<List<CommentReview>> getCommentsByRoomingHouseId(@Path("roomingHouseId") String roomingHouseId, @Header("Authorization") String token);

    @GET("comment/tenant/{roomingHouseId}")
    Call<List<CommentReview>> getCommentsByRoomingtenantId(@Path("roomingHouseId") String roomingHouseId, @Header("Authorization") String token);

}
