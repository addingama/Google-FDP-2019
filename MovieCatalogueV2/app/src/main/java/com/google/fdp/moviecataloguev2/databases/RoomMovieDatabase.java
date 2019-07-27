package com.google.fdp.moviecataloguev2.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@Database(entities = {FavouriteMovie.class, FavouriteTvShow.class}, version = 1, exportSchema = false)
public abstract class RoomMovieDatabase extends RoomDatabase {

        public abstract FavouriteMovieDao favouriteMovieDao();
        public abstract FavouriteTvShowDao favouriteTvShowDao();

        private static RoomMovieDatabase INSTANCE = null;

        public static RoomMovieDatabase getAppDatabase(Context context) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomMovieDatabase.class, "movie-catalogue-db")
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
