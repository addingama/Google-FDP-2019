package com.google.fdp.favouritemovie;

import android.database.Cursor;

/**
 * Created by gama on 2019-09-07.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public interface LoadMoviesCallback {
    void postExecute(Cursor movies);
}
