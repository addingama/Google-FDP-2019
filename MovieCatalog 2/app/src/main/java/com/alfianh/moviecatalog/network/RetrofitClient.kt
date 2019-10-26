package com.alfianh.moviecatalog.network

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object  RetrofitClient {

  fun <S> createService(serviceClass: Class<S>): S {
    return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
      .addConverterFactory(GsonConverterFactory.create()).build().create(serviceClass)
  }

}