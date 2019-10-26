package com.google.fdp.moviecatalogue.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by gama on 2019-07-24.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@Database(entities = {FavouriteMovie.class,}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {
    public abstract FavouriteMovieDao favouriteMovieDao();

    private static MovieRoomDatabase INSTANCE = null;

    public static MovieRoomDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieRoomDatabase.class, "movie-catalogue-db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

