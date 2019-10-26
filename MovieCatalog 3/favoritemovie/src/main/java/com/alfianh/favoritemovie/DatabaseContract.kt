package com.alfianh.favoritemovie

import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {

  private val AUTHORITY = "com.alfianh.moviecatalog.database.provider"
  private val SCHEME = "content"

  class MovieColumns : BaseColumns {
    companion object {
      private val TABLE_NAME = "movie"
      val ID = "id"
      val TITLE = "title"
      val OVERVIEW = "overview"
      val RELEASED = "released"
      val LANGUAGE = "language"
      val POSTER = "poster"
      val RATING = "rating"
      val BACKDROP = "backdrop"
      val CONTENT_URI = Uri.Builder().scheme(SCHEME).authority(AUTHORITY).appendPath(
          TABLE_NAME).build()
    }
  }

  fun getColumnString(cursor: Cursor, columnName: String): String {
    return cursor.getString(cursor.getColumnIndex(columnName))
  }

  fun getColumnInt(cursor: Cursor, columnName: String): Int {
    return cursor.getInt(cursor.getColumnIndex(columnName))
  }

  fun getColumnLong(cursor: Cursor, columnName: String): Long {
    return cursor.getLong(cursor.getColumnIndex(columnName))
  }

  fun getColumnFloat(cursor: Cursor, columnName: String): Float {
    return cursor.getFloat(cursor.getColumnIndex(columnName))
  }
}