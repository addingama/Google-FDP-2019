package com.alfianh.moviecatalog.network

import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.models.response.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

  @GET("discover/movie")
  fun getMovies(@Query("api_key") apiKey: String): Call<BaseResponse<Movie>>

  @GET("discover/tv")
  fun getTvShows(@Query("api_key") apiKey: String): Call<BaseResponse<Movie>>

}