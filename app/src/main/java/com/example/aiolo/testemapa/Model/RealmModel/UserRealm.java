package com.example.aiolo.testemapa.Model.RealmModel;

import io.realm.RealmObject;

public class UserRealm extends RealmObject {

    private String email;

    public UserRealm(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
