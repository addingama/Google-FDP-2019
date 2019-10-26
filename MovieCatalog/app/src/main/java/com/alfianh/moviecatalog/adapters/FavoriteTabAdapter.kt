package com.alfianh.moviecatalog.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FavoriteTabAdapter constructor(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  private val fragments: MutableList<Fragment> = mutableListOf()
  private val titles: MutableList<String> = mutableListOf()

  override fun getItem(position: Int): Fragment = fragments[position]

  override fun getCount(): Int = fragments.size

  override fun getPageTitle(position: Int): CharSequence? = titles[position]

  fun addFragment(fragment: Fragment, title: String) {
    fragments.add(fragment)
    titles.add(title)
  }

}