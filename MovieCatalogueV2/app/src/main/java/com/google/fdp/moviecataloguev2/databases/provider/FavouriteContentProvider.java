package com.google.fdp.moviecataloguev2.databases.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.fdp.moviecataloguev2.databases.FavouriteMovie;
import com.google.fdp.moviecataloguev2.databases.FavouriteMovieDao;
import com.google.fdp.moviecataloguev2.databases.RoomMovieDatabase;

/**
 * Created by gama on 2019-09-07.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class FavouriteContentProvider extends ContentProvider {
    private String AUTHORITY = "com.google.fdp.moviecataloguev2.databases.provider";
    private int MOVIE = 1;
    private UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private FavouriteMovieDao favoriteMovieDao;

    public FavouriteContentProvider() {
        matcher.addURI(AUTHORITY, FavouriteMovie.TABLE_NAME, MOVIE);
    }


    @Override
    public boolean onCreate() {
        favoriteMovieDao = RoomMovieDatabase.getAppDatabase(this.getContext()).favouriteMovieDao();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return favoriteMovieDao.getAllFavoriteMoviesForContentProvider();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
