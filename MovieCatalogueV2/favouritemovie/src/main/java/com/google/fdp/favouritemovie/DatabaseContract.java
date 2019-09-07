package com.google.fdp.favouritemovie;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by gama on 2019-09-07.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class DatabaseContract {

    private static final String AUTHORITY = "com.google.fdp.moviecataloguev2.databases.provider";
    private static final String SCHEME = "content";

    static final class MovieColumns implements BaseColumns {
        private static final String TABLE_NAME = "movie";

         static final String ID = "id";
         static final String TITLE = "title";
         static final String OVERVIEW = "overview";
         static final String RELEASED = "released";
         static final String LANGUAGE = "language";
         static final String POSTER = "poster";
         static final String RATING = "rating";
         static final String BACKDROP = "backdrop";
         static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static float getColumnFloat(Cursor cursor, String columnName) {
        return cursor.getFloat(cursor.getColumnIndex(columnName));
    }
}