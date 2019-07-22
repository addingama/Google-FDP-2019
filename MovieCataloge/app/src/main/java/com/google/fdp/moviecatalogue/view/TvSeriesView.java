package com.google.fdp.moviecatalogue.view;

import com.google.fdp.moviecatalogue.model.TvSerie;

import java.util.ArrayList;

/**
 * Created by gama on 2019-07-01.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public interface TvSeriesView {
    void showMovies(ArrayList<TvSerie> data);
    void showLoading(boolean isLoading);
    void showError(String message);
}
