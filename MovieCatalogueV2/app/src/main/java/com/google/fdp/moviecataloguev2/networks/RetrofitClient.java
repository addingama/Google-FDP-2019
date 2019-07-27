package com.google.fdp.moviecataloguev2.networks;

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
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class RetrofitClient {
    public <S> S createService(Class<S> serviceClass) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttp().build())
                .build();

        return retrofit.create(serviceClass);
    }

    private OkHttpClient.Builder getOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("Movie Catalogue", message);
            }
        });

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(interceptor);



        builder.connectTimeout(360, TimeUnit.SECONDS);
        builder.readTimeout(360, TimeUnit.SECONDS);
        builder.writeTimeout(360, TimeUnit.SECONDS);

        return builder;
    }
}
