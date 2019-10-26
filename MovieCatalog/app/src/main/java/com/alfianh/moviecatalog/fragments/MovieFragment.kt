package com.alfianh.moviecatalog.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfianh.moviecatalog.MovieDetailActivity
import com.alfianh.moviecatalog.R
import com.alfianh.moviecatalog.adapters.MovieAdapter
import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

  private val movies: MutableList<Movie> = mutableListOf()
  private lateinit var adapter: MovieAdapter
  private lateinit var viewModel: MovieViewModel

  companion object {
    private const val TYPE_KEY = "TYPE"
    private const val IS_FAVORITE_KEY = "IS_FAVORITE_MENU"
    const val MOVIE_KEY = "MOVIE"
    const val TV_SHOW_KEY = "TV_SHOW"
    fun newInstance(type: String, isFavorite: Boolean = false): MovieFragment {
      return MovieFragment().apply {
        arguments = Bundle().apply {
          putString(TYPE_KEY, type)
          putBoolean(IS_FAVORITE_KEY, isFavorite)
        }
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = activity?.let {
      ViewModelProviders.of(it).get(MovieViewModel::class.java)
    } ?: ViewModelProviders.of(this).get(MovieViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_movie, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = MovieAdapter(context, movies) {
      MovieDetailActivity.startActivity(context, movies[it],
          arguments?.getString(TYPE_KEY) ?: MOVIE_KEY)
    }
    rvMovie.layoutManager = LinearLayoutManager(context)
    rvMovie.adapter = adapter
    if (arguments?.getBoolean(IS_FAVORITE_KEY, false) == true) {
      loadFavorite()
    } else {
      loadMovies()
    }
  }

  private fun loadMovies() {
    viewModel.getLiveData().observe(this, Observer {
      onComplete(it)
    })
    updateItem()
  }

  private fun updateItem() {
    showLoading(true)
    viewModel.updateItem(arguments?.getString(TYPE_KEY) ?: MOVIE_KEY)
  }

  private fun loadFavorite() {
    showLoading(true)
    viewModel.getAllFavorite(arguments?.getString(TYPE_KEY) ?: MOVIE_KEY).observe(this,
        Observer { onComplete(it) })
  }

  private fun onComplete(movies: List<Movie>?) {
    showLoading(false)
    movies?.let { response ->
      this.movies.clear()
      response.forEach { movie -> this.movies.add(movie) }
      adapter.notifyDataSetChanged()
    }
  }

  private fun showLoading(state: Boolean) {
    if (state) {
      pbLoading.visibility = View.VISIBLE
    } else {
      pbLoading.visibility = View.GONE
    }
  }

}