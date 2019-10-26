package com.alfianh.moviecatalog.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel constructor(application: Application) : AndroidViewModel(application) {

  private var movieRepository: MovieRepository = MovieRepository.getInstance(getApplication())

  fun getLiveData(): LiveData<List<Movie>> = movieRepository.getAllMovies()

  fun updateItem(type: String) = movieRepository.updateData(type)

  fun getAllFavorite(type: String) = movieRepository.getAllFavorite(type)

  fun insertFavorite(movie: Movie, type: String) = viewModelScope.launch {
    movieRepository.insertFavorite(movie, type)
  }

  fun deleteFavorite(movie: Movie, type: String) = viewModelScope.launch {
    movieRepository.deleteFavorite(movie, type)
  }

  fun isFavorite(id: Long, type: String) = movieRepository.isFavorite(id, type)

}