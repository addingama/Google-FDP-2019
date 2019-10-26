package com.google.fdp.moviecatalogue.databases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by gama on 2019-07-25.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@lombok.Data
@Entity(tableName = "favorite_movies")
public class FavouriteMovie {
    @PrimaryKey
    public Integer id;
    public String title;
    public String releaseDate;
    public String overview;
    public String originalLanguage;
    public String posterPath;
    public Float voteAverage = 0.0f;
    public String backdropPath;

}
