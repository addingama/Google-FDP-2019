package com.alfianh.moviecatalog.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.AlarmManager
import android.app.PendingIntent
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DailyReminderManager : BroadcastReceiver() {

  companion object {
    const val MESSAGE_KEY = "MESSAGE_KEY"
    const val TITLE_KEY = "TITLE_KEY"
    const val ID_REPEATING_DAILY = 1002
    const val TIME_FORMAT = "HH:mm"
  }

  override fun onReceive(context: Context, intent: Intent) {
    val title = intent.getStringExtra(TITLE_KEY)
    val message = intent.getStringExtra(MESSAGE_KEY)
    NotificationHelper(context).createNotification(title, message)
  }

  fun setRepeatingAlarm(context: Context, title: String, message: String, time: String) {
    if (isDateInvalid(time, TIME_FORMAT)) return
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val alarmIntent = Intent(context, DailyReminderManager::class.java).let { intent ->
      intent.putExtra(TITLE_KEY, title)
      intent.putExtra(MESSAGE_KEY, message)
      PendingIntent.getBroadcast(context, ID_REPEATING_DAILY, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, time.split(":")[0].toInt())
    calendar.set(Calendar.MINUTE, time.split(":")[1].toInt())
    calendar.set(Calendar.SECOND, 0)
    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
        AlarmManager.INTERVAL_DAY, alarmIntent)
  }

  fun cancelAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val pendingIntent = Intent(context, DailyReminderManager::class.java).let {
      PendingIntent.getBroadcast(context, ID_REPEATING_DAILY, it, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    pendingIntent.cancel()
    alarmManager.cancel(pendingIntent)
  }

  private fun isDateInvalid(date: String, format: String): Boolean {
    return try {
      val df = SimpleDateFormat(format, Locale.getDefault())
      df.isLenient = false
      df.parse(date)
      false
    } catch (e: ParseException) {
      true
    }
  }
}