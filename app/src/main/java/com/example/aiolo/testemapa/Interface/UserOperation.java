package com.example.aiolo.testemapa.Interface;

import com.example.aiolo.testemapa.Model.User;
import com.example.aiolo.testemapa.Model.APIResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserOperation {

    @POST("usuario/register")
    Call<APIResponse> saveUser(@Body User user);

    @POST("usuario/login")
    Call<APIResponse> loginUser(@Body User user);
}
