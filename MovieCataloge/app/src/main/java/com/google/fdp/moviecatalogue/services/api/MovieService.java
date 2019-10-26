package com.google.fdp.moviecatalogue.services.api;

import com.google.fdp.moviecatalogue.model.Movie;
import com.google.fdp.moviecatalogue.model.responses.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public interface MovieService {


    @GET("3/discover/movie")
    Call<BaseResponse<Movie>> getMovies(@Query("api_key") String apiKey);


    @GET("3/discover/tv")
    Call<BaseResponse<Movie>> getTvSeries(@Query("api_key") String apiKey);

}
