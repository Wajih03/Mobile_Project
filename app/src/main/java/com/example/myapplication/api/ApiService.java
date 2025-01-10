package com.example.myapplication.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Endpoint for the signup API
    @POST("/signup")
    Call<ApiResponse> signup(@Body HelperClass user);

    // Endpoint for the login API
    @POST("/login")
    Call<ApiResponse> login(@Body LoginCredentials credentials);
}
