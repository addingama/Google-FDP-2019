package com.google.fdp.moviecataloguev2.databases;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
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

    public FavouriteTvShow(Long id, String title, String released, String overview, String language, String poster, float rating, String backdrop) {
        this.id = id;
        this.title = title;
        this.released = released;
        this.overview = overview;
        this.language = language;
        this.poster = poster;
        this.rating = rating;
        this.backdrop = backdrop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}