package com.alfianh.moviecatalog.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.alfianh.moviecatalog.R
import com.alfianh.moviecatalog.service.StackWidgetService

/**
 * Implementation of App Widget functionality.
 */
class FavoriteMovieWidget : AppWidgetProvider() {

  override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager,
      appWidgetIds: IntArray) {
    for (appWidgetId in appWidgetIds) {
      updateAppWidget(context, appWidgetManager, appWidgetId)
    }
  }

  override fun onEnabled(context: Context) {}

  override fun onDisabled(context: Context) {}

  override fun onReceive(context: Context?, intent: Intent?) {
    super.onReceive(context, intent)
    if (intent?.action != null) {
      if (intent.action == ACTION_CLICK) {
        val movie = intent.getStringExtra(EXTRA_ITEM)
        Toast.makeText(context, movie, Toast.LENGTH_SHORT).show()
      }
    }
  }

  companion object {

    private const val ACTION_CLICK = "com.alfianh.moviecatalog.ACTION_CLICK"
    const val EXTRA_ITEM = "com.alfianh.moviecatalog.EXTRA_ITEM"

    internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
        appWidgetId: Int) {
      val intent = Intent(context, StackWidgetService::class.java)
      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
      intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
      val views = RemoteViews(context.packageName, R.layout.favorite_movie_widget)
      views.setRemoteAdapter(R.id.stack_view, intent)
      views.setEmptyView(R.id.stack_view, R.id.empty_view)
      val toastIntent = Intent(context, FavoriteMovieWidget::class.java)
      toastIntent.action = ACTION_CLICK
      toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
      intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
      val toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent,
          PendingIntent.FLAG_UPDATE_CURRENT)
      views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent)

      appWidgetManager.updateAppWidget(appWidgetId, views)
    }
  }
}

