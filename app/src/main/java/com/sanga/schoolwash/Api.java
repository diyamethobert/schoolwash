package com.sanga.schoolwash;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;

    public static Retrofit getApi(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
