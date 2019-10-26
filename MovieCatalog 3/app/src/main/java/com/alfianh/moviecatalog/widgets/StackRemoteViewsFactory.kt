package com.alfianh.moviecatalog.widgets

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.alfianh.moviecatalog.R
import android.content.Intent
import android.os.Bundle
import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.widgets.FavoriteMovieWidget.Companion.EXTRA_ITEM
import android.graphics.BitmapFactory
import android.os.Binder
import com.alfianh.moviecatalog.repository.MovieRepository
import java.net.URL

class StackRemoteViewsFactory constructor(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory {

  private val items: MutableList<Movie> = mutableListOf()

  override fun onCreate() {}

  override fun getLoadingView(): RemoteViews? = null

  override fun getItemId(position: Int): Long = 0

  override fun hasStableIds(): Boolean = false

  override fun onDestroy() {}

  override fun onDataSetChanged() {
    val token = Binder.clearCallingIdentity()
    try {
      items.clear()
      MovieRepository(context).getAllFavoriteMovieForWidget().forEach {
        items.add(it)
      }
    } finally {
      Binder.restoreCallingIdentity(token)
    }
  }

  override fun getViewAt(position: Int): RemoteViews {
    val rv = RemoteViews(context.packageName, R.layout.widget_item)
    val imageUrl = URL(items[position].posterFullUrl)
    rv.setImageViewBitmap(R.id.imageView,
        BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream()))
    val extras = Bundle()
    extras.putString(EXTRA_ITEM, items[position].title)
    val fillInIntent = Intent()
    fillInIntent.putExtras(extras)
    rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
    return rv
  }

  override fun getCount(): Int = items.size

  override fun getViewTypeCount(): Int = 1
}