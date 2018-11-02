package com.example.aiolo.testemapa.ModelRealm;

import io.realm.RealmObject;

public class UserObject extends RealmObject {

    private String userName;
    private String lastName;
    private String city;
    private String state;
    private String martialState;
    private String motocycle;
    private String emailUser;
    private String pass;

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
