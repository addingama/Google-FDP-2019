package com.google.fdp.moviecataloguev2.databases;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public interface FavouriteTvShowDao {
    @Query("SELECT * FROM favourite_tv_show")
    List<FavouriteTvShow> getAll();

    @Insert
    void insertAll(FavouriteTvShow... users);

    @Delete
    void delete(FavouriteTvShow movie);
}
