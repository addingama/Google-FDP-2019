package com.google.fdp.favouritemovie;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gama on 2019-09-07.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@lombok.Data
public class Movie implements Parcelable {
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName(value = "title", alternate = "name")
    @Expose
    public String title;
    @SerializedName(value = "release_date", alternate = "first_air_date")
    @Expose
    public String releaseDate;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("vote_average")
    @Expose
    public float rating = 0.0f;

    public Movie() {
    }

     Movie(Cursor cursor) {
        id = DatabaseContract.getColumnLong(cursor, DatabaseContract.MovieColumns.ID);
        title = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.TITLE);
        releaseDate = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.RELEASED);
        overview = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.OVERVIEW);
        originalLanguage = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.LANGUAGE);
        posterPath = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.POSTER);
        rating = DatabaseContract.getColumnFloat(cursor, DatabaseContract.MovieColumns.RATING);
        backdropPath = DatabaseContract.getColumnString(cursor, DatabaseContract.MovieColumns.BACKDROP);
    }

    public Movie(Long id, String title, String releaseDate, String overview, String originalLanguage, String posterPath, String backdropPath, float rating) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.rating = rating;
    }

    public String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w185/" + posterPath;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeFloat(this.rating);
    }

    protected Movie(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.rating = in.readFloat();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


}
