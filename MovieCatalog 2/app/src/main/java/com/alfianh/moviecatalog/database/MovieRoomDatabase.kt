package com.alfianh.moviecatalog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovie::class, FavoriteTvShow::class], version = 1)
abstract class MovieRoomDatabase : RoomDatabase() {

  abstract fun favoriteMovieDao(): FavoriteMovieDao
  abstract fun favoriteTvShowDao(): FavoriteTvShowDao

  companion object {
    private var INSTANCE: MovieRoomDatabase? = null
    fun getDatabase(context: Context): MovieRoomDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext, MovieRoomDatabase::class.java, "movies_database"
        ).fallbackToDestructiveMigration().build()
        INSTANCE = instance
        instance
      }
    }
  }

}