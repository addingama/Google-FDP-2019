package com.google.fdp.moviecataloguev2.networks;

import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.models.responses.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public interface MovieService {
    @GET("3/discover/movie")
    Call<BaseResponse<Movie>> getMovies(@Query("api_key") String apiKey);


    @GET("3/discover/tv")
    Call<BaseResponse<Movie>> getTvSeries(@Query("api_key") String apiKey);

}

