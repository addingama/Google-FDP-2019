package com.alfianh.favoritemovie

import android.database.Cursor
import android.os.Parcelable
import com.alfianh.favoritemovie.DatabaseContract.getColumnFloat
import com.alfianh.favoritemovie.DatabaseContract.getColumnInt
import com.alfianh.favoritemovie.DatabaseContract.getColumnLong
import com.alfianh.favoritemovie.DatabaseContract.getColumnString
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Long? = null,
    val title: String? = null,
    val released: String? = null,
    val overview: String? = null,
    val language: String? = null,
    val poster: String? = null,
    val rating: Float = 0.0f,
    val backdrop: String? = null
) : Parcelable {
  companion object {
    fun fromCursor(cursor: Cursor) : Movie {
      return Movie(
          id = getColumnLong(cursor, DatabaseContract.MovieColumns.ID),
          title = getColumnString(cursor, DatabaseContract.MovieColumns.TITLE),
          released = getColumnString(cursor, DatabaseContract.MovieColumns.RELEASED),
          overview = getColumnString(cursor, DatabaseContract.MovieColumns.OVERVIEW),
          language = getColumnString(cursor, DatabaseContract.MovieColumns.LANGUAGE),
          poster = getColumnString(cursor, DatabaseContract.MovieColumns.POSTER),
          rating = getColumnFloat(cursor, DatabaseContract.MovieColumns.RATING),
          backdrop = getColumnString(cursor, DatabaseContract.MovieColumns.BACKDROP)
      )
    }
  }
}