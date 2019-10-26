package com.google.fdp.moviecatalogue.presenter;

import com.google.fdp.moviecatalogue.services.api.RetrofitClient;
import com.google.fdp.moviecatalogue.services.api.MovieService;
import com.google.gson.Gson;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class BasePresenter {
    protected MovieService service;
    protected Gson gson;

    public BasePresenter() {
        this.service = RetrofitClient.getInstance().getService();
        this.gson = new Gson();
    }
}
