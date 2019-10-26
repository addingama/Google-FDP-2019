package com.alfianh.moviecatalog.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alfianh.moviecatalog.R
import com.alfianh.moviecatalog.adapters.FavoriteTabAdapter
import com.alfianh.moviecatalog.fragments.MovieFragment.Companion.MOVIE_KEY
import com.alfianh.moviecatalog.fragments.MovieFragment.Companion.TV_SHOW_KEY
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_favorite, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fragmentManager?.let {
      val tabAdapter = FavoriteTabAdapter(it)
      tabAdapter.addFragment(
        MovieFragment.newInstance(MOVIE_KEY, true), getString(R.string.title_menu_movie)
      )
      tabAdapter.addFragment(
        MovieFragment.newInstance(TV_SHOW_KEY, true), getString(R.string.title_menu_tv_show)
      )
      viewPager.adapter = tabAdapter
      tabLayout.setupWithViewPager(viewPager)
    }
  }
}