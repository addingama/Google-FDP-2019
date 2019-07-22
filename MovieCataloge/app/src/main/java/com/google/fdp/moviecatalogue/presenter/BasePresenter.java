package com.google.fdp.moviecatalogue.presenter;

import com.google.fdp.moviecatalogue.services.api.MovieDbClient;
import com.google.fdp.moviecatalogue.services.api.MovieDbEndpoints;
import com.google.gson.Gson;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class BasePresenter {
    protected MovieDbEndpoints service;
    protected Gson gson;

    public BasePresenter() {
        this.service = MovieDbClient.getInstance().getService();
        this.gson = new Gson();
    }
}
