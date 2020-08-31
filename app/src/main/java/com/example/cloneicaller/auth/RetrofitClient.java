package com.example.cloneicaller.auth;

import com.readystatesoftware.chuck.ChuckInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://icaller.grooo.com.vn/v1/";
    public static AuthService service;


    public static AuthService authService;

    public static AuthService getInstance() {

        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(AuthService.class);
        }
        return service;
    }
}
