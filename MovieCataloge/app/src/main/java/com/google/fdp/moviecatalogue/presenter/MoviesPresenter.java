package com.google.fdp.moviecatalogue.presenter;

import com.google.fdp.moviecatalogue.model.Movie;
import com.google.fdp.moviecatalogue.model.responses.MoviesResponse;
import com.google.fdp.moviecatalogue.view.MoviesView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gama on 2019-07-01.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MoviesPresenter extends BasePresenter{
    private MoviesView view;
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public MoviesPresenter(MoviesView view) {
        this.view = view;
    }


    public void fetchMovies() {
        view.showLoading(true);
        this.service.getMovies().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                view.showLoading(false);
                if (response.body() != null) {
                    movies = response.body().results;
                    view.showMovies(response.body().results);
                } else {
                    view.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                view.showLoading(false);
                view.showError(t.getMessage());
            }
        });
    }

    public void fetchTvSeries() {
        view.showLoading(true);
        this.service.getTvSeries().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                view.showLoading(false);
                if (response.body() != null) {
                    movies = response.body().results;
                    view.showMovies(response.body().results);
                } else {
                    view.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                view.showLoading(false);
                view.showError(t.getMessage());
            }
        });
    }

}
