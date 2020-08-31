package com.example.cloneicaller.auth;

import com.example.cloneicaller.Models.Members;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {

    @FormUrlEncoded
    @POST("auth/login")
    Call<Members> getMember(@Field("phone") String phone, @Field("g_token") String g_token);

    //Get phone DB
    @GET("phone/get-db")
    Call<String> getData(@Query("limit") Integer limit,
                           @Query("updated_at") String updated_at,
                           @Query("paging") Integer paging,
                           @Query("page") Integer page,
                           @Query("select") String select,
                           @Query("sort") String sort,
                           @Query("direction") String direction);

}
