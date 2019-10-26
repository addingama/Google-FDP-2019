package com.alfianh.moviecatalog.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.alfianh.moviecatalog.BuildConfig.API_KEY
import com.alfianh.moviecatalog.database.*
import com.alfianh.moviecatalog.fragments.MovieFragment
import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.models.response.BaseResponse
import com.alfianh.moviecatalog.network.MovieService
import com.alfianh.moviecatalog.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository constructor(application: Application) {

  private val favoriteMovieDao: FavoriteMovieDao = MovieRoomDatabase.getDatabase(
      application).favoriteMovieDao()
  private val favoriteTvShowDao: FavoriteTvShowDao = MovieRoomDatabase.getDatabase(
      application).favoriteTvShowDao()
  private var mutableLiveData = MutableLiveData<List<Movie>>()
  private var service: MovieService = RetrofitClient.createService(MovieService::class.java)

  companion object {
    private var INSTANCE: MovieRepository? = null
    fun getInstance(application: Application): MovieRepository {
      return INSTANCE ?: synchronized(MovieRepository::class) {
        val instance = MovieRepository(application)
        INSTANCE = instance
        instance
      }
    }
  }

  fun getAllMovies(): LiveData<List<Movie>> {
    return mutableLiveData
  }

  fun isFavorite(id: Long, type: String) = Transformations.map(
      if (type == MovieFragment.MOVIE_KEY) {
        favoriteMovieDao.findById(id)
      } else {
        favoriteTvShowDao.findById(id)
      }) {
    it.isNotEmpty()
  }

  fun updateData(type: String) {
    if (type == MovieFragment.MOVIE_KEY) {
      service.getMovies(API_KEY)
    } else {
      service.getTvShows(API_KEY)
    }.enqueue(object : Callback<BaseResponse<Movie>> {
      override fun onFailure(call: Call<BaseResponse<Movie>>, t: Throwable) {
        mutableLiveData.postValue(null)
      }

      override fun onResponse(call: Call<BaseResponse<Movie>>,
          response: Response<BaseResponse<Movie>>) {
        if (response.isSuccessful) {
          mutableLiveData.postValue(response.body()?.results)
        }
      }
    })
  }

  fun getAllFavorite(type: String): LiveData<List<Movie>> {
    if (type == MovieFragment.MOVIE_KEY) {
      return Transformations.map(favoriteMovieDao.getAllFavoriteMovies()) {
        val movies: MutableList<Movie> = mutableListOf()
        it.forEach { favorite ->
          movies.add(constructFavoriteToMovie(favorite))
        }
        movies
      }
    } else {
      return Transformations.map(favoriteTvShowDao.getAllFavoriteTvShows()) {
        val tvShows: MutableList<Movie> = mutableListOf()
        it.forEach { favorite ->
          tvShows.add(constructFavoriteToMovie(favorite))
        }
        tvShows
      }
    }
  }

  suspend fun insertFavorite(favorite: Movie, type: String) {
    if (type == MovieFragment.MOVIE_KEY) {
      favoriteMovieDao.insert(
          FavoriteMovie(id = favorite.id, title = favorite.title, released = favorite.released,
              poster = favorite.poster, language = favorite.language, rating = favorite.rating,
              backdrop = favorite.backdrop, overview = favorite.overview))
    } else {
      favoriteTvShowDao.insert(
          FavoriteTvShow(id = favorite.id, title = favorite.title, released = favorite.released,
              poster = favorite.poster, language = favorite.language, rating = favorite.rating,
              backdrop = favorite.backdrop, overview = favorite.overview))
    }
  }

  suspend fun deleteFavorite(favorite: Movie, type: String) {
    if (type == MovieFragment.MOVIE_KEY) {
      favoriteMovieDao.delete(
          FavoriteMovie(id = favorite.id, title = favorite.title, released = favorite.released,
              poster = favorite.poster, language = favorite.language, rating = favorite.rating,
              backdrop = favorite.backdrop, overview = favorite.overview))
    } else {
      favoriteTvShowDao.delete(
          FavoriteTvShow(id = favorite.id, title = favorite.title, released = favorite.released,
              poster = favorite.poster, language = favorite.language, rating = favorite.rating,
              backdrop = favorite.backdrop, overview = favorite.overview))
    }
  }

  private fun constructFavoriteToMovie(favorite: Any): Movie = when (favorite) {
    is FavoriteMovie -> Movie(id = favorite.id, title = favorite.title,
        released = favorite.released, poster = favorite.poster, language = favorite.language,
        rating = favorite.rating, backdrop = favorite.backdrop, overview = favorite.overview)
    is FavoriteTvShow -> Movie(id = favorite.id, title = favorite.title,
        released = favorite.released, poster = favorite.poster, language = favorite.language,
        rating = favorite.rating, backdrop = favorite.backdrop, overview = favorite.overview)
    else -> Movie()
  }


}