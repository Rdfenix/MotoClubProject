package com.moto.aiolo.motoclubproject.Model;

import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("title")
    private String titleGroup;
    @SerializedName("description")
    private String descGroup;
    @SerializedName("moto_category")
    private String motoCategory;
    @SerializedName("city")
    private String cityGroup;
    @SerializedName("state")
    private String stateGroup;
    @SerializedName("qdt_members")
    private String qdtMembers;
    @SerializedName("id_usuario")
    private Integer idUser;

    public Group(String titleGroup, String descGroup, String motoCategory,
                 String cityGroup, String stateGroup, String qdtMembers, Integer idUser) {
        this.titleGroup = titleGroup;
        this.descGroup = descGroup;
        this.motoCategory = motoCategory;
        this.cityGroup = cityGroup;
        this.stateGroup = stateGroup;
        this.qdtMembers = qdtMembers;
        this.idUser = idUser;
    }

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
}
