package com.alfianh.moviecatalog.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao interface FavoriteTvShowDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE) suspend fun insert(movie: FavoriteTvShow)

  @Delete suspend fun delete(movie: FavoriteTvShow)

  @Query("SELECT * FROM favorite_tv_show")
  fun getAllFavoriteTvShows(): LiveData<List<FavoriteTvShow>>

  @Query("SELECT * FROM favorite_tv_show WHERE id = :id")
  fun findById(id: Long): LiveData<List<FavoriteTvShow>>

}