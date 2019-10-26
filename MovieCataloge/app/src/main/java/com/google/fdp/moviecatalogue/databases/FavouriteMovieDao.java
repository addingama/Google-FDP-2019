package com.google.fdp.moviecatalogue.databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by gama on 2019-07-25.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@Dao
public interface FavouriteMovieDao {
    @Query("SELECT * FROM favorite_movies")
    List<FavouriteMovie> getAll();

    @Insert
    void insertAll(FavouriteMovie... users);

    @Delete
    void delete(FavouriteMovie movie);
}
