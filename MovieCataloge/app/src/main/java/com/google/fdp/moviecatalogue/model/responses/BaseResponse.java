package com.google.fdp.moviecatalogue.model.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class BaseResponse<T> {
    @SerializedName("results")
    @Expose
    public ArrayList<T> results = null;
}
