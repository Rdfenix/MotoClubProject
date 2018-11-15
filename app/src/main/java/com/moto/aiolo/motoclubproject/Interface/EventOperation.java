package com.moto.aiolo.motoclubproject.Interface;


import com.moto.aiolo.motoclubproject.Model.Event;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.APIResponse;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.EventResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EventOperation {

    @POST("evento/register")
    Call<APIResponse> saveEvent(@Body Event event);

    @GET("evento/list")
    Call<List<EventResponse>> getAllEvents();

}
