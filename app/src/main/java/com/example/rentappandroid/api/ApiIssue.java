package com.example.rentappandroid.api;

import com.example.rentappandroid.Dto.Reponse.AreaInformationReponse;
import com.example.rentappandroid.Dto.Request.Schema.IssueRequest;
import com.example.rentappandroid.Global.ValueGlobal;
import com.example.rentappandroid.Model.Issue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiIssue {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .setLenient()
            .create();


    ApiIssue apiApiIssue = new Retrofit.Builder()

            .baseUrl(ValueGlobal.address)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiIssue.class);

    @GET("issue/owner/{id}")
    Call<List<Issue>> getListIssueOwner(@Path("id") String id, @Header("Authorization") String token);

    @GET("issue/tenant/{id}")
    Call<List<Issue>> getListIssuetenant(@Path("id") String id, @Header("Authorization") String token);

    @PATCH("issue/update-status/{id}/{status}")
    Call<String> update(@Path("id") String id,@Path("status") String status, @Header("Authorization") String token);

    @POST("issue")
    Call<Void> createIssue(@Body IssueRequest request, @Header("Authorization") String token);

}
