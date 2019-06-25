package com.sanga.schoolwash.Database;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("report.php")
    Call<List<FormData>> getReport();

    @GET("most_read.php")
    Call<List<Users>> getMostRead();

    @GET("category.php")
    Call<List<Users>> getCategory(@Query("category") String category);

    @FormUrlEncoded
    @POST("login.php")
    Call<Users>getLogin(
            @Field("email") String keyword,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<Users> updateStatus(@Field("id") int id);

    @FormUrlEncoded
    @POST("new_data.php")
    Call<Users> saveToDatabase(
            @Field("school") String school,
            @Field("head") String head,
            @Field("female") String female,
            @Field("male") String male,
            @Field("staff") String staff,
            @Field("fToilets") String fToilets,
            @Field("mToilets") String mToilets,
            @Field("sToilets") String sToilets,
            @Field("taps") String taps,
            @Field("dustBins") String dustBins
    );

    @FormUrlEncoded
    @POST("new_user.php")
    Call<Users> register(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );
}
