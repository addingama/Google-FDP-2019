package com.google.fdp.moviecataloguev2.databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
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
    LiveData<List<FavouriteMovie>> getAllFavoriteMovies();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavouriteMovie movie);

    @Delete()
    void delete(FavouriteMovie movie);

    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    LiveData<List<FavouriteMovie>> findById(Long id);

    @Query("SELECT * FROM favourite_movies")
    List<FavouriteMovie> getAllFavoriteMoviesForWidget();

}
