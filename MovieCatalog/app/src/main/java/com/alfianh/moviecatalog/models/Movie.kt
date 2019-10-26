package com.alfianh.moviecatalog.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("title", alternate = ["name"])
    val title: String? = null,
    @SerializedName("release_date", alternate = ["first_air_date"])
    val released: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("original_language")
    val language: String? = null,
    @SerializedName("poster_path")
    val poster: String? = null,
    @SerializedName("vote_average")
    val rating: Float = 0.0f,
    @SerializedName("backdrop_path")
    val backdrop: String? = null
) : Parcelable {
    val posterFullUrl : String
      get() = "https://image.tmdb.org/t/p/original$poster"
}