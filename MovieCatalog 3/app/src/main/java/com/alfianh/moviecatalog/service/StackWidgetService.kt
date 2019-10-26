package com.alfianh.moviecatalog.service

import android.content.Intent
import android.widget.RemoteViewsService
import com.alfianh.moviecatalog.widgets.StackRemoteViewsFactory

class StackWidgetService : RemoteViewsService() {
  override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory = StackRemoteViewsFactory(
      applicationContext)

}