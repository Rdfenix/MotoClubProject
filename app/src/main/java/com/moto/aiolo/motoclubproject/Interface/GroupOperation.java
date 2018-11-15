package com.moto.aiolo.motoclubproject.Interface;


import com.moto.aiolo.motoclubproject.Model.Group;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.APIResponse;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.GroupResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GroupOperation {

    @Headers({"content-type: application/json;charset=UTF-8"})
    @POST("grupo/register")
    Call<APIResponse> registerGroup(@Body Group group);

    @GET("grupo/list")
    Call<List<GroupResponse>> getAllGroups();
}
