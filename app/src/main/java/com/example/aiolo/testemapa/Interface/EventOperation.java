package com.example.aiolo.testemapa.Interface;

import com.example.aiolo.testemapa.Model.Event;
import com.example.aiolo.testemapa.Model.ResponseModel.APIResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventOperation {

    @POST("evento/register")
    Call<APIResponse> saveEvent(@Body Event event);

}
