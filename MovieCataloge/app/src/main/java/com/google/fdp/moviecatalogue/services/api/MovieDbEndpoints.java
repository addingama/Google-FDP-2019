package com.google.fdp.moviecatalogue.services.api;

import com.google.fdp.moviecatalogue.BuildConfig;
import com.google.fdp.moviecatalogue.model.responses.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public interface MovieDbEndpoints {

    String apiKey = BuildConfig.TMDB_API_KEY;

    @GET("3/discover/movie?api_key=" + apiKey)
    Call<MoviesResponse> getMovies();


    @GET("3/discover/tv?api_key=" + apiKey)
    Call<MoviesResponse> getTvSeries();

}
