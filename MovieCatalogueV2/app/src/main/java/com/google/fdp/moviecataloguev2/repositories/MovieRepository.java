package com.google.fdp.moviecataloguev2.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.fdp.moviecataloguev2.BuildConfig;
import com.google.fdp.moviecataloguev2.databases.FavouriteMovieDao;
import com.google.fdp.moviecataloguev2.databases.RoomMovieDatabase;
import com.google.fdp.moviecataloguev2.fragments.MovieFragment;
import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.models.responses.BaseResponse;
import com.google.fdp.moviecataloguev2.networks.MovieService;
import com.google.fdp.moviecataloguev2.networks.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieRepository {
    private FavouriteMovieDao favouriteMovieDao;
    private MutableLiveData<List<Movie>> mutableLiveData;
    private MovieService service;

    private static MovieRepository INSTANCE;


    public MovieRepository(Application application) {
        favouriteMovieDao = RoomMovieDatabase.getAppDatabase(application).favouriteMovieDao();
        mutableLiveData = new MutableLiveData<List<Movie>>();
        service = new RetrofitClient().createService(MovieService.class);
    }

    public static  synchronized  MovieRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new MovieRepository(application);
        }

        return INSTANCE;
    }



    public LiveData<List<Movie>> getAllMovies() {
        return mutableLiveData;
    }

    public void updateData(String type) {
        if (type == MovieFragment.MOVIE_KEY) {
            service.getMovies(BuildConfig.API_KEY).enqueue(moviesCallback);
        } else {
            service.getTvSeries(BuildConfig.API_KEY).enqueue(moviesCallback);
        }
    }

    private Callback<BaseResponse<Movie>> moviesCallback = new Callback<BaseResponse<Movie>>() {
        @Override
        public void onResponse(Call<BaseResponse<Movie>> call, Response<BaseResponse<Movie>> response) {
            if(response.isSuccessful()) {
                mutableLiveData.postValue(response.body().results);
            }
        }

        @Override
        public void onFailure(Call<BaseResponse<Movie>> call, Throwable t) {
            mutableLiveData.postValue(null);
        }
    };
}
