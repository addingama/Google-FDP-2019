package com.google.fdp.moviecatalogue.presenter;

import com.google.fdp.moviecatalogue.model.TvSerie;
import com.google.fdp.moviecatalogue.model.responses.TvSeriesResponse;
import com.google.fdp.moviecatalogue.view.TvSeriesView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gama on 2019-07-01.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class TvSeriesPresenter extends BasePresenter{
    private TvSeriesView view;
    private ArrayList<TvSerie> movies = new ArrayList<TvSerie>();

    public TvSeriesPresenter(TvSeriesView view) {
        this.view = view;
    }


    public void fetchTvSeries() {
        view.showLoading(true);
        this.service.getTvSeries().enqueue(new Callback<TvSeriesResponse>() {
            @Override
            public void onResponse(Call<TvSeriesResponse> call, Response<TvSeriesResponse> response) {
                view.showLoading(false);
                if (response.body() != null) {
                    movies = response.body().results;
                    view.showMovies(response.body().results);
                } else {
                    view.showError(response.message());
                }
            }

            @Override
            public void onFailure(Call<TvSeriesResponse> call, Throwable t) {
                view.showLoading(false);
                view.showError(t.getMessage());
            }
        });
    }

    public TvSerie getMovie(int index) {
        return movies.get(index);
    }
}
