package com.google.fdp.moviecatalogue;

import android.app.Application;

import com.google.fdp.moviecatalogue.databases.AppDatabase;

/**
 * Created by gama on 2019-07-24.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieCatalogueApplication extends Application {
    public static AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();

//        db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "movie-catalogue").fallbackToDestructiveMigration()
//                .build();


    }
}
