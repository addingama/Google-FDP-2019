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


    public LiveData<List<Movie>> getAllFavourite(String type) {
        return movieRepository.getAllFavourite(type);
    }

    public void insertFavourite(Movie data, String type) {
        movieRepository.insertFavourite(data, type);
    }

    public void deleteFavourite(Long id, String type) {
        movieRepository.deleteFavourite(id, type);
    }

    public LiveData<Boolean> isFavourite(Long id, String type) {
        return movieRepository.isFavourite(id, type);
    }

}
