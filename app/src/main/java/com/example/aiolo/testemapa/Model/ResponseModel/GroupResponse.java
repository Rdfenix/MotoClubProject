package com.example.aiolo.testemapa.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupResponse {

    public String getTitleGroup() {
        return titleGroup;
    }

    public void setTitleGroup(String titleGroup) {
        this.titleGroup = titleGroup;
    }

    public String getDescGroup() {
        return descGroup;
    }

    public void setDescGroup(String descGroup) {
        this.descGroup = descGroup;
    }

    public String getMotoCategory() {
        return motoCategory;
    }

    public void setMotoCategory(String motoCategory) {
        this.motoCategory = motoCategory;
    }

    public String getCityGroup() {
        return cityGroup;
    }

    public void setCityGroup(String cityGroup) {
        this.cityGroup = cityGroup;
    }

    public String getStateGroup() {
        return stateGroup;
    }

    public void setStateGroup(String stateGroup) {
        this.stateGroup = stateGroup;
    }

    public String getQdtMembers() {
        return qdtMembers;
    }

    public void setQdtMembers(String qdtMembers) {
        this.qdtMembers = qdtMembers;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("id")
    @Expose
    private Integer id;

    public GroupResponse(Integer id, String titleGroup, String descGroup, String motoCategory, String cityGroup, String stateGroup, String qdtMembers, Integer idUser) {
        this.id = id;
        this.titleGroup = titleGroup;
        this.descGroup = descGroup;
        this.motoCategory = motoCategory;
        this.cityGroup = cityGroup;
        this.stateGroup = stateGroup;
        this.qdtMembers = qdtMembers;
        this.idUser = idUser;
    }

    @SerializedName("title")
    @Expose
    private String titleGroup;
    @SerializedName("description")
    @Expose
    private String descGroup;
    @SerializedName("moto_category")
    @Expose
    private String motoCategory;
    @SerializedName("city")
    @Expose
    private String cityGroup;
    @SerializedName("state")
    @Expose
    private String stateGroup;
    @SerializedName("qdt_members")
    @Expose
    private String qdtMembers;
    @SerializedName("id_usuario")
    @Expose
    private Integer idUser;

}
