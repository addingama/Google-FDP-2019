package com.google.fdp.moviecataloguev2.databases;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@Dao
public interface FavouriteTvShowDao {
    @Query("SELECT * FROM favourite_tv_show")
    LiveData<List<FavouriteTvShow>> getAll();

    @Insert
    void insert(FavouriteTvShow movie);

    @Query("DELETE from favourite_tv_show WHERE id = :id")
    void delete(Long id);

    @Query("SELECT * FROM favourite_tv_show WHERE id = :id")
    LiveData<List<FavouriteTvShow>> findById(Long id);
}
