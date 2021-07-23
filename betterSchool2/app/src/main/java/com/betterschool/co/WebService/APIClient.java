package com.betterschool.co.WebService;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient  {
    
    public static final String BASE_URL = "http://192.168.1.33:1000/api/v1/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {

            Request newRequest  = chain.request().newBuilder()
                    .addHeader("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoibW9oYW1tYWQgamF2YWQiLCJsYXN0TmFtZSI6ImhvamF0aSIsImRlcGFydG1lbnQiOiJoZWFkbWFzdGVyIiwiaWF0IjoxNjI2ODY1MTM4fQ.xQsQaZ2TsFfuD4dVbVLsgSfy1vlDiMjDWkTBsLXXe3U")
                    .addHeader("semester", "1")
                    .build();
            
            return chain.proceed(newRequest);
        })
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
        }
        return retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
