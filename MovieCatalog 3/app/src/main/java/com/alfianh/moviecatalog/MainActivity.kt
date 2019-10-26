package com.alfianh.moviecatalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alfianh.moviecatalog.fragments.FavoriteFragment
import com.alfianh.moviecatalog.fragments.MovieFragment
import com.alfianh.moviecatalog.fragments.MovieFragment.Companion.MOVIE_KEY
import com.alfianh.moviecatalog.fragments.MovieFragment.Companion.TV_SHOW_KEY
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    openFragment(MovieFragment.newInstance(MOVIE_KEY))
  }

  private fun openFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.flContainer, fragment,
        fragment.javaClass.simpleName).commit()
  }

  private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
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

}
