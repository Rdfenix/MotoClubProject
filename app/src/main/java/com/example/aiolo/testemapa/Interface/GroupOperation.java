package com.example.aiolo.testemapa.Interface;

import com.example.aiolo.testemapa.Model.APIResponse;
import com.example.aiolo.testemapa.Model.Group;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GroupOperation {

    @Headers({"content-type: application/json;charset=UTF-8"})
    @POST("grupo/register")
    Call<APIResponse> registerGroup(@Body Group group);
}
