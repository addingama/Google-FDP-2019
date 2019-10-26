package com.google.fdp.moviecatalogue.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.fdp.moviecatalogue.databases.FavouriteMovieDao;
import com.google.fdp.moviecatalogue.databases.MovieRoomDatabase;
import com.google.fdp.moviecatalogue.model.Movie;
import com.google.fdp.moviecatalogue.services.api.MovieService;
import com.google.fdp.moviecatalogue.services.api.RetrofitClient;

import java.util.List;

/**
 * Created by gama on 2019-07-25.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieRepository {

    private FavouriteMovieDao favouriteMovieDao;
    private MutableLiveData<List<Movie>> mutableLiveData;
    private MovieService service;


    public MovieRepository(Application application) {
        favouriteMovieDao = MovieRoomDatabase.getAppDatabase(application).favouriteMovieDao();
        mutableLiveData = new MutableLiveData<List<Movie>>();
        service = new RetrofitClient().createService(MovieService.class);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return mutableLiveData;
    }
}
