package com.example.aiolo.testemapa.Mthods;

import android.text.TextUtils;

public class ValidateItems {

    public boolean textEmpty(String txt){
        return TextUtils.isEmpty(txt);
    }

    public boolean isEmail(String email) {
        return email.contains("@");
    }
}
