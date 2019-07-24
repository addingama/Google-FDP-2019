package com.google.fdp.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created by gama on 2019-07-22.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@Data
public class TvSerie implements Parcelable {
    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("origin_country")
    @Expose
    public List<String> originCountry = null;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("vote_average")
    @Expose
    public Double voteAverage;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    public String getPosterUrl() {
        return "https://image.tmdb.org/t/p/w185/" + posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalName);
        dest.writeList(this.genreIds);
        dest.writeString(this.name);
        dest.writeValue(this.popularity);
        dest.writeStringList(this.originCountry);
        dest.writeValue(this.voteCount);
        dest.writeString(this.firstAirDate);
        dest.writeString(this.backdropPath);
        dest.writeString(this.originalLanguage);
        dest.writeValue(this.id);
        dest.writeValue(this.voteAverage);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
    }

    public TvSerie() {
    }

    protected TvSerie(Parcel in) {
        this.originalName = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.name = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.originCountry = in.createStringArrayList();
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.firstAirDate = in.readString();
        this.backdropPath = in.readString();
        this.originalLanguage = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
        this.overview = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<TvSerie> CREATOR = new Parcelable.Creator<TvSerie>() {
        @Override
        public TvSerie createFromParcel(Parcel source) {
            return new TvSerie(source);
        }

        @Override
        public TvSerie[] newArray(int size) {
            return new TvSerie[size];
        }
    };
}
