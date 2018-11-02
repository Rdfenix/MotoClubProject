package com.example.aiolo.testemapa.RealmModel;

import io.realm.RealmObject;

public class UserRealm extends RealmObject {


    private int idUser;
    private String nameUser;
    private String lastName;
    private String userCity;
    private String userState;
    private String maritialState;
    private String userMotocycle;
    private String emailUser;
    private String passUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
}
