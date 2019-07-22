package com.google.fdp.moviecatalogue.model.responses;

import com.google.fdp.moviecatalogue.model.TvSerie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class TvSeriesResponse {
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("results")
    @Expose
    public ArrayList<TvSerie> results = null;
}
