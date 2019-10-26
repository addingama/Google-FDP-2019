package com.alfianh.moviecatalog

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alfianh.moviecatalog.fragments.FavoriteFragment
import com.alfianh.moviecatalog.fragments.MovieFragment
import com.alfianh.moviecatalog.fragments.MovieFragment.Companion.MOVIE_KEY
import com.alfianh.moviecatalog.fragments.MovieFragment.Companion.TV_SHOW_KEY
import com.alfianh.moviecatalog.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    openFragment(MovieFragment.newInstance(MOVIE_KEY))
  }

  private fun openFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
      .replace(R.id.flContainer, fragment, fragment.javaClass.simpleName).commit()
  }

  private val onNavigationItemSelectedListener =
    BottomNavigationView.OnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.navigation_movie -> {
          openFragment(MovieFragment.newInstance(MOVIE_KEY))
          return@OnNavigationItemSelectedListener true
        }
        R.id.navigation_tv_show -> {
          openFragment(MovieFragment.newInstance(TV_SHOW_KEY))
          return@OnNavigationItemSelectedListener true
        }
        R.id.navigation_favorite -> {
          openFragment(FavoriteFragment())
          return@OnNavigationItemSelectedListener true
        }
      }
      false
    }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.setting_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.menu_language_setting -> {
        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
      }
    }
    return super.onOptionsItemSelected(item)
  }

}
