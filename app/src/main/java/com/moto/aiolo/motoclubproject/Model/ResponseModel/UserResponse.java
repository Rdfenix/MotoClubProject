package com.moto.aiolo.motoclubproject.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("id")
    @Expose
    private int idUser;

    @SerializedName("name_user")
    @Expose
    private String nameUser;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("city")
    @Expose
    private String userCity;

    @SerializedName("state")
    @Expose
    private String userState;

    @SerializedName("maritial_state")
    @Expose
    private String maritialState;

    @SerializedName("motocycle")
    @Expose
    private String userMotocycle;

    @SerializedName("email_user")
    @Expose
    private String emailUser;

    @SerializedName("pass_user")
    @Expose
    private String passUser;

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("msg")
    @Expose
    private String msg;

    public UserResponse(int idUser, String nameUser, String lastName, String userCity, String userState,
                        String maritialState, String userMotocycle,
                        String emailUser, String passUser, Integer status, String msg) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.lastName = lastName;
        this.userCity = userCity;
        this.userState = userState;
        this.maritialState = maritialState;
        this.userMotocycle = userMotocycle;
        this.emailUser = emailUser;
        this.passUser = passUser;
        this.status = status;
        this.msg = msg;
    }

    public UserResponse(Integer status, String msg){
        this.status = status;
        this.msg = msg;
    }


    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getMaritialState() {
        return maritialState;
    }

    public void setMaritialState(String maritialState) {
        this.maritialState = maritialState;
    }

    public String getUserMotocycle() {
        return userMotocycle;
    }

    public void setUserMotocycle(String userMotocycle) {
        this.userMotocycle = userMotocycle;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPassUser() {
        return passUser;
    }

    public void setPassUser(String passUser) {
        this.passUser = passUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
