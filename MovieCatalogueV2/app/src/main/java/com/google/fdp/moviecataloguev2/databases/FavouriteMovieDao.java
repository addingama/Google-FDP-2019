package com.google.fdp.moviecataloguev2.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@Dao
public interface FavouriteMovieDao {
    @Query("SELECT * FROM favourite_movies")
    List<FavouriteMovie> getAll();

    @Insert
    void insertAll(FavouriteMovie... users);

    @Delete
    void delete(FavouriteMovie movie);
}
