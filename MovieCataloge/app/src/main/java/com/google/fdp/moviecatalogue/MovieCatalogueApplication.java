package com.google.fdp.moviecatalogue;

import android.app.Application;

import com.google.fdp.moviecatalogue.databases.MovieRoomDatabase;

/**
 * Created by gama on 2019-07-24.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieCatalogueApplication extends Application {
    public static MovieRoomDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();

//        db = Room.databaseBuilder(getApplicationContext(),
//                MovieRoomDatabase.class, "movie-catalogue").fallbackToDestructiveMigration()
//                .build();


    }
}
