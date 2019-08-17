package com.google.fdp.moviecataloguev2.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.fdp.moviecataloguev2.BuildConfig;
import com.google.fdp.moviecataloguev2.databases.FavouriteMovie;
import com.google.fdp.moviecataloguev2.databases.FavouriteMovieDao;
import com.google.fdp.moviecataloguev2.databases.FavouriteTvShow;
import com.google.fdp.moviecataloguev2.databases.FavouriteTvShowDao;
import com.google.fdp.moviecataloguev2.databases.RoomMovieDatabase;
import com.google.fdp.moviecataloguev2.fragments.MovieFragment;
import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.models.responses.BaseResponse;
import com.google.fdp.moviecataloguev2.networks.MovieService;
import com.google.fdp.moviecataloguev2.networks.RetrofitClient;

import java.util.ArrayList;
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
    private FavouriteTvShowDao favouriteTvShowDao;
    private MutableLiveData<List<Movie>> mutableLiveData;
    private MovieService service;
    private List<Movie> defaultResponse = new ArrayList<Movie>();

    private static MovieRepository INSTANCE;


    public MovieRepository(Application application) {
        favouriteMovieDao = RoomMovieDatabase.getAppDatabase(application).favouriteMovieDao();
        favouriteTvShowDao = RoomMovieDatabase.getAppDatabase(application).favouriteTvShowDao();
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
        if (MovieFragment.MOVIE_KEY.equals(type)) {
            service.getMovies(BuildConfig.API_KEY).enqueue(moviesCallback);
        } else {
            service.getTvSeries(BuildConfig.API_KEY).enqueue(moviesCallback);
        }
    }

    public void searchData(String type, String keyword) {
        if (MovieFragment.MOVIE_KEY.equals(type)) {
            service.searchMovies(BuildConfig.API_KEY, keyword).enqueue(moviesCallback);
        } else {
            service.searchTvSeries(BuildConfig.API_KEY, keyword).enqueue(moviesCallback);
        }
    }

    private Callback<BaseResponse<Movie>> moviesCallback = new Callback<BaseResponse<Movie>>() {
        @Override
        public void onResponse(Call<BaseResponse<Movie>> call, Response<BaseResponse<Movie>> response) {
            if(response.isSuccessful()) {
                if (response.body() != null) {
                    mutableLiveData.postValue(response.body().results);
                }

            }
        }

        @Override
        public void onFailure(Call<BaseResponse<Movie>> call, Throwable t) {
            mutableLiveData.postValue(defaultResponse);
        }
    };

    public LiveData<Boolean> isFavourite(Long id, String type) {
        if (MovieFragment.MOVIE_KEY.equals(type)) {
            return Transformations.map(favouriteMovieDao.findById(id),
                it -> !it.isEmpty()
            );
        } else {
            return Transformations.map(favouriteTvShowDao.findById(id),
                    it -> !it.isEmpty()
            );
        }
    }

    public void insertFavourite(Movie data, String type) {
        if (MovieFragment.MOVIE_KEY.equals(type)) {
            favouriteMovieDao.insert(new FavouriteMovie(
                    data.getId(),
                    data.getTitle(),
                    data.getReleaseDate(),
                    data.getOverview(),
                    data.getOriginalLanguage(),
                    data.getPosterPath(),
                    data.getRating(),
                    data.getBackdropPath()
            ));
        } else {
            favouriteTvShowDao.insert(new FavouriteTvShow(
                    data.getId(),
                    data.getTitle(),
                    data.getReleaseDate(),
                    data.getOverview(),
                    data.getOriginalLanguage(),
                    data.getPosterPath(),
                    data.getRating(),
                    data.getBackdropPath()
            ));
        }
    }

    public void deleteFavourite(Movie data, String type) {
        if (MovieFragment.MOVIE_KEY.equals(type)) {
            favouriteMovieDao.delete(new FavouriteMovie(
                    data.getId(),
                    data.getTitle(),
                    data.getReleaseDate(),
                    data.getOverview(),
                    data.getOriginalLanguage(),
                    data.getPosterPath(),
                    data.getRating(),
                    data.getBackdropPath()
            ));
        } else {
            favouriteTvShowDao.delete(new FavouriteTvShow(
                    data.getId(),
                    data.getTitle(),
                    data.getReleaseDate(),
                    data.getOverview(),
                    data.getOriginalLanguage(),
                    data.getPosterPath(),
                    data.getRating(),
                    data.getBackdropPath()
            ));
        }
    }

    public LiveData<List<Movie>> getAllFavourite(String type) {

        if (MovieFragment.MOVIE_KEY.equals(type)) {

            return Transformations.map(favouriteMovieDao.getAllFavoriteMovies(), it -> {
                List<Movie> movies = new ArrayList<Movie>();
                for (FavouriteMovie favouriteMovie : it) {
                    movies.add(constructFavouriteToMovie(favouriteMovie));
                }
                return movies;
            });
        } else {

            return Transformations.map(favouriteTvShowDao.getAllFavoriteTvShows(), it -> {
                List<Movie> tvSeries = new ArrayList<Movie>();
                for (FavouriteTvShow favouriteTvShow : it) {
                    tvSeries.add(constructFavouriteToMovie(favouriteTvShow));
                }
                return tvSeries;
            });
        }
    }

    private Movie constructFavouriteToMovie(Object favourite) {
        if (favourite instanceof FavouriteMovie) {
            FavouriteMovie data = (FavouriteMovie) favourite;
            return new Movie(
                    data.getId(),
                    data.getTitle(),
                    data.getReleased(),
                    data.getOverview(),
                    data.getLanguage(),
                    data.getPoster(),
                    data.getBackdrop(),
                    data.getRating()
            );
        } else if (favourite instanceof FavouriteTvShow) {
            FavouriteTvShow data = (FavouriteTvShow) favourite;
            return new Movie(
                    data.getId(),
                    data.getTitle(),
                    data.getReleased(),
                    data.getOverview(),
                    data.getLanguage(),
                    data.getPoster(),
                    data.getBackdrop(),
                    data.getRating()
            );
        } else {
            return new Movie();
        }
    }
}
