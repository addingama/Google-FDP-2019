package com.alfianh.moviecatalog.network

import com.alfianh.moviecatalog.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

  fun <S> createService(serviceClass: Class<S>): S {

    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
      val original = chain.request()
      val originalHttpUrl = original.url()
      val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
      val requestBuilder = original.newBuilder().url(url)
      val request = requestBuilder.build()
      chain.proceed(request)
    }

    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
        .create(serviceClass)
  }

}