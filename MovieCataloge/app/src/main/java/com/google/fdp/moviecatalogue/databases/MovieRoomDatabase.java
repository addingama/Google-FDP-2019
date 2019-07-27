package com.google.fdp.moviecatalogue.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by gama on 2019-07-24.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@Database(entities = {FavouriteMovie.class,}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FavouriteMovie favouriteMovieDao();

    private 
}

