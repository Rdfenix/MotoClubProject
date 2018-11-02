package com.example.aiolo.testemapa.Interface;

import com.example.aiolo.testemapa.Model.User;
import com.example.aiolo.testemapa.Model.ResponseModel.APIResponse;
import com.example.aiolo.testemapa.Model.ResponseModel.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserOperation {

    @POST("usuario/register")
    Call<APIResponse> saveUser(@Body User user);

    @POST("usuario/login")
    Call<APIResponse> loginUser(@Body User user);

    @GET("usuario/{email}")
    Call<UserResponse> getUser(@Path("email") String email);
}
