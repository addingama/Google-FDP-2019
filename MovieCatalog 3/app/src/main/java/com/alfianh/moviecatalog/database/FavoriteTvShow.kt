package com.alfianh.moviecatalog.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tv_show")
data class FavoriteTvShow(
    @PrimaryKey
    val id: Long? = null,
    val title: String? = null,
    val released: String? = null,
    val overview: String? = null,
    val language: String? = null,
    val poster: String? = null,
    val rating: Float = 0.0f,
    val backdrop: String? = null
)