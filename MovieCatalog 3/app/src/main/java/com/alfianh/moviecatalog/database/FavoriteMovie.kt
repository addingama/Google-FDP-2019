package com.alfianh.moviecatalog.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alfianh.moviecatalog.database.FavoriteMovie.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FavoriteMovie(
    @PrimaryKey
    val id: Long? = null,
    val title: String? = null,
    val released: String? = null,
    val overview: String? = null,
    val language: String? = null,
    val poster: String? = null,
    val rating: Float = 0.0f,
    val backdrop: String? = null
) {
    companion object {
        const val TABLE_NAME = "favorite_movies"
    }
}