package com.google.fdp.moviecataloguev2.models.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class BaseResponse<T> {
    @SerializedName("results")
    @Expose
    public ArrayList<T> results = null;
}
