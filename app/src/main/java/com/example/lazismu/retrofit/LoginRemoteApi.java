package com.example.lazismu.retrofit;

import com.example.lazismu.retrofit.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginRemoteApi {
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_name") String deviceName
    );
}
