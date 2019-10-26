package com.alfianh.moviecatalog.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao interface FavoriteMovieDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun insert(movie: FavoriteMovie)

  @Delete suspend fun delete(movie: FavoriteMovie)

  @Query("SELECT * FROM favorite_movies") fun getAllFavoriteMovies(): LiveData<List<FavoriteMovie>>

  @Query("SELECT * FROM favorite_movies WHERE id = :id")
  fun findById(id: Long): LiveData<List<FavoriteMovie>>
}