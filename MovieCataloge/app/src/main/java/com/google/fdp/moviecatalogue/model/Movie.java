package com.google.fdp.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gama on 2019-07-01.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class Movie implements Parcelable {
    private String title;
    private String image;
    private String date;
    private String description;

    public Movie() {
    }

    public Movie(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Movie(String title, String image, String date, String description) {
        this.title = title;
        this.image = image;
        this.date = date;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.date);
        dest.writeString(this.description);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.image = in.readString();
        this.date = in.readString();
        this.description = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
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
