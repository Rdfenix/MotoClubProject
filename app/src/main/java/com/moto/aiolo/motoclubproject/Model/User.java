package com.moto.aiolo.motoclubproject.Model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name_user")
    private String userName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("maritial_state")
    private String martialState;

    @SerializedName("motocycle")
    private String motocycle;

    @SerializedName("email_user")
    private String emailUser;

    @SerializedName("pass_user")
    private String pass;

    public User(String userName, String lastName, String city, String state,
                String martialState, String motocycle, String emailUser, String pass) {
        this.userName = userName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.martialState = martialState;
        this.motocycle = motocycle;
        this.emailUser = emailUser;
        this.pass = pass;
    }

    public User(String emailUser, String pass){
        this.emailUser = emailUser;
        this.pass = pass;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMartialState() {
        return martialState;
    }

    public void setMartialState(String martialState) {
        this.martialState = martialState;
    }

    public String getMotocycle() {
        return motocycle;
    }

    public void setMotocycle(String motocycle) {
        this.motocycle = motocycle;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
