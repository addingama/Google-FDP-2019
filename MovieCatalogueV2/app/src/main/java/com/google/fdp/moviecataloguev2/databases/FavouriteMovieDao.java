package com.google.fdp.moviecataloguev2.databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
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
    LiveData<List<FavouriteMovie>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavouriteMovie movie);

    @Query("DELETE from favourite_movies WHERE id = :id")
    void delete(Long id);

    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    LiveData<List<FavouriteMovie>> findById(Long id);
}
