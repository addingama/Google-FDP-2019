package com.alfianh.moviecatalog.database.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.alfianh.moviecatalog.database.FavoriteMovie
import com.alfianh.moviecatalog.database.MovieRoomDatabase
import com.alfianh.moviecatalog.database.FavoriteMovieDao

class FavoriteContentProvider : ContentProvider() {

  private val AUTHORITY = "com.alfianh.moviecatalog.database.provider"
  private val MOVIE = 1
  private val matcher = UriMatcher(UriMatcher.NO_MATCH)
  private lateinit var favoriteMovieDao: FavoriteMovieDao

  init {
    matcher.addURI(AUTHORITY, FavoriteMovie.TABLE_NAME, MOVIE)
  }

  override fun onCreate(): Boolean {
    favoriteMovieDao = MovieRoomDatabase.getDatabase(context).favoriteMovieDao()
    return true
  }

  override fun query(uri: Uri, p1: Array<String>?, p2: String?, p3: Array<String>?,
      p4: String?): Cursor? {
    return favoriteMovieDao.getAllFavoriteMoviesForContentProvider()
  }

  override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<String>?): Int = 0

  override fun delete(p0: Uri, p1: String?, p2: Array<String>?): Int = 0

  override fun insert(p0: Uri, p1: ContentValues?): Uri? = null

  override fun getType(uri: Uri): String? = null
}