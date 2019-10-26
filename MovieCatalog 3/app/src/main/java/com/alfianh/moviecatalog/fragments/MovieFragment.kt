package com.alfianh.moviecatalog.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfianh.moviecatalog.MovieDetailActivity
import com.alfianh.moviecatalog.adapters.MovieAdapter
import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import android.content.Context.SEARCH_SERVICE
import android.app.SearchManager
import android.content.Intent
import android.provider.Settings
import androidx.appcompat.widget.SearchView
import com.alfianh.moviecatalog.R
import com.alfianh.moviecatalog.SettingActivity

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
    setHasOptionsMenu(true)
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
      MovieDetailActivity.startActivity(context, movies[it], getType())
    }
    rvMovie.layoutManager = LinearLayoutManager(context)
    rvMovie.adapter = adapter
    if (isFavoriteMenu()) {
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
    viewModel.updateItem(getType())
  }

  private fun loadFavorite() {
    showLoading(true)
    viewModel.getAllFavorite(getType()).observe(this, Observer { onComplete(it) })
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

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?) {
    menu.clear()
    if (isFavoriteMenu()) {
      inflater?.inflate(R.menu.setting_menu, menu)
    } else {
      inflater?.inflate(R.menu.option_menu, menu)
      val searchManager = context?.getSystemService(SEARCH_SERVICE) as SearchManager?
      if (searchManager != null) {
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
          override fun onQueryTextSubmit(query: String): Boolean {
            showLoading(true)
            viewModel.searchItem(getType(), query)
            return true
          }

          override fun onQueryTextChange(newText: String): Boolean {
            if (newText.isBlank()) updateItem()
            return false
          }
        })
      }
    }
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.menu_language_setting -> {
        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
      }
      R.id.menu_reminder_setting -> {
        startActivity(Intent(activity, SettingActivity::class.java))
      }
    }
    return super.onOptionsItemSelected(item)
  }

  private fun getType(): String = arguments?.getString(TYPE_KEY) ?: MOVIE_KEY

  private fun isFavoriteMenu(): Boolean = arguments?.getBoolean(IS_FAVORITE_KEY, false) ?: false

}