package com.example.test_exam.api;

import com.example.test_exam.model.BlinkData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConnectApi {
    Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();

    ConnectApi apiservice = new Retrofit.Builder()
            .baseUrl("https://64d59338613ee4426d977185.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ConnectApi.class);

    @GET("blink")
    Call<List<BlinkData>> getdata();


    @POST("blink")
    Call<List<BlinkData>> addData(@Body BlinkData blinkData);


    @DELETE("blink/{id}")
    Call<List<BlinkData>> deleteData(@Path("id") String id) ;


    @PUT("blink/{id}")
    Call<Void> editData(@Path("id") String id, @Body BlinkData blinkData);

}
