package com.reza.dinosaurus.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {

    private static final String alamatServer = "https://rezarestian.000webhostapp.com/";
    private static Retrofit retro;

    public static Retrofit koneksiRetrofit(){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(alamatServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}