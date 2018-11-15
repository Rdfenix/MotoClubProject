package com.moto.aiolo.motoclubproject.Interface;



import com.moto.aiolo.motoclubproject.Model.ResponseModel.APIResponse;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.UserResponse;
import com.moto.aiolo.motoclubproject.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserOperation {

    @POST("usuario/register")
    Call<APIResponse> saveUser(@Body User user);

    /*@POST("usuario/login")
    Call<APIResponse> loginUser(@Body User user);*/

    @POST("usuario/login")
    Call<UserResponse> loginUser(@Body User user);

    @GET("usuario/{email}")
    Call<UserResponse> getUser(@Path("email") String email);
}
