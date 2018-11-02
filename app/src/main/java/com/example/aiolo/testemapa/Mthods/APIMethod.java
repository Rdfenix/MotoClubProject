package com.example.aiolo.testemapa.Mthods;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIMethod {


    /*adicionar nesse local a URL da api que esta sendo usada
        ou caso esteja em um servidor local adicionar o ipv4 da sua maquina*/
    private static String BASE_URL = "http://192.168.0.13/motoClubAPI/v1/";

    public static Retrofit API(){

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
