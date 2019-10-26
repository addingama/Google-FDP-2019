package com.google.fdp.moviecatalogue.databases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
@lombok.Data
@Entity(
        tableName = "favourite_tv_show"
)
public final class FavouriteTvShow {
    @PrimaryKey
    private Long id;
    private String title;
    private String released;
    private String overview;
    private String language;
    private String poster;
    private float rating = 0.0f;
    private String backdrop;
}