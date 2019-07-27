package com.google.fdp.moviecataloguev2.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@lombok.Data
public class Movie implements Parcelable {
    @SerializedName("id")
    @Expose
    public Integer id;
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
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
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
