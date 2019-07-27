package com.google.fdp.moviecataloguev2.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.repositories.MovieRepository;

import java.util.List;

/**
 * Created by gama on 2019-07-27.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
    }


    public LiveData<List<Movie>> getLiveData() {
        return movieRepository.getAllMovies();
    }

    public void updateItem(String type) {
        movieRepository.updateData(type);
    }



}
