package com.google.fdp.moviecatalogue.services.api;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieDbClient {
    private MovieDbEndpoints service;
    private static MovieDbClient client = null;


    public MovieDbClient() {
         Retrofit retrofit;
         Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttp().build())
                .build();
        service = retrofit.create(MovieDbEndpoints.class);
    }

    public static MovieDbClient getInstance() {
        if (client == null) {
            client = new MovieDbClient();
        }
        return client;
    }

    private OkHttpClient.Builder getOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("Movie DB", message);
            }
        });

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(interceptor);



        builder.connectTimeout(360, TimeUnit.SECONDS);
        builder.readTimeout(360, TimeUnit.SECONDS);
        builder.writeTimeout(360, TimeUnit.SECONDS);

        return builder;
    }

    public MovieDbEndpoints getService() {
        return service;
    }
}
