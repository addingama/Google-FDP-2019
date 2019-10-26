package com.alfianh.moviecatalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.viewmodels.MovieViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

  private lateinit var viewModel: MovieViewModel
  private lateinit var movie: Movie
  private var isFavorite = false

  companion object {
    private const val MOVIE_DATA_KEY = "MOVIE_DATA"
    private const val TYPE_KEY = "TYPE"
    fun startActivity(context: Context?, movie: Movie, type: String) {
      context?.startActivity(Intent(context, MovieDetailActivity::class.java).apply {
        putExtra(MOVIE_DATA_KEY, movie)
        putExtra(TYPE_KEY, type)
      })
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_detail)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    intent.getParcelableExtra<Movie>(MOVIE_DATA_KEY)?.let {
      movie = it
      title = it.title
      Glide.with(this).load(it.posterFullUrl).into(ivPoster)
      tvTitle.text = it.title
      tvReleased.text = it.released
      tvLanguage.text = getString(R.string.language, it.language)
      tvRating.text = getString(R.string.rating, it.rating.toString())
      rbRating.rating = it.rating
      tvOverviewValue.text = it.overview
    }
    viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.detail_menu, menu)
    viewModel.isFavorite(movie.id ?: 0, intent.getStringExtra(TYPE_KEY)).observe(this, Observer {
      isFavorite = it
      if (it == true) {
        menu.findItem(R.id.navigation_favorite).icon = ContextCompat.getDrawable(this,
            R.drawable.ic_favorite_black_24dp)
      } else {
        menu.findItem(R.id.navigation_favorite).icon = ContextCompat.getDrawable(this,
            R.drawable.ic_favorite_border_black_24dp)
      }
    })
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> {
        finish()
      }
      R.id.navigation_favorite -> {
        if (isFavorite) {
          viewModel.deleteFavorite(movie, intent.getStringExtra(TYPE_KEY))
        } else {
          viewModel.insertFavorite(movie, intent.getStringExtra(TYPE_KEY))
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }
}