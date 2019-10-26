package com.alfianh.moviecatalog.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.provider.Settings.System.DEFAULT_NOTIFICATION_URI
import androidx.core.app.NotificationCompat
import com.alfianh.moviecatalog.MainActivity
import com.alfianh.moviecatalog.R

class NotificationHelper(private val mContext: Context) {
  private lateinit var mNotificationManager: NotificationManager
  private lateinit var mBuilder: NotificationCompat.Builder

  fun createNotification(title: String?, message: String?) {
    val resultIntent = Intent(mContext, MainActivity::class.java)
    resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    val resultPendingIntent = PendingIntent.getActivity(mContext,1000, resultIntent,
      PendingIntent.FLAG_UPDATE_CURRENT);

    mBuilder = NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID)
    mBuilder.setSmallIcon(R.mipmap.ic_launcher)
    mBuilder.setContentTitle(title).setContentText(message).setAutoCancel(false).setSound(
        DEFAULT_NOTIFICATION_URI).setContentIntent(resultPendingIntent).setAutoCancel(true)

    mNotificationManager = mContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      val importance = NotificationManager.IMPORTANCE_HIGH
      val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID,
          NOTIFICATION_CHANNEL_NAME, importance)
      notificationChannel.enableLights(true)
      notificationChannel.lightColor = Color.RED
      notificationChannel.enableVibration(true)
      notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200,
          400)
      mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
      mNotificationManager.createNotificationChannel(notificationChannel)
    }
    mNotificationManager.notify(System.currentTimeMillis().toInt(), mBuilder.build())
  }

  companion object {
    const val NOTIFICATION_CHANNEL_ID = "10001"
    const val NOTIFICATION_CHANNEL_NAME = "df"
  }
}
