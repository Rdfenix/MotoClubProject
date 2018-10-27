package com.example.aiolo.testemapa.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResponse {

    @SerializedName("status")
    @Expose
    private Integer statusApi;
    @SerializedName("msg")
    @Expose
    private String msg;

    public APIResponse(Integer statusApi, String msg) {
        this.statusApi = statusApi;
        this.msg = msg;
    }

    public Integer getStatusApi() {
        return statusApi;
    }

    public void setStatusApi(Integer statusApi) {
        this.statusApi = statusApi;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
