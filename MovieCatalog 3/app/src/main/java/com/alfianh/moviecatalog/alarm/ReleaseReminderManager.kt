package com.alfianh.moviecatalog.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.AlarmManager
import android.app.PendingIntent
import android.util.Log
import com.alfianh.moviecatalog.R
import com.alfianh.moviecatalog.models.Movie
import com.alfianh.moviecatalog.models.response.BaseResponse
import com.alfianh.moviecatalog.network.MovieService
import com.alfianh.moviecatalog.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ReleaseReminderManager : BroadcastReceiver() {

  companion object {
    const val ID_REPEATING_RELEASE = 1001
    const val DATE_FORMAT = "yyyy-MM-dd"
    const val TIME_FORMAT = "HH:mm"
  }

  override fun onReceive(context: Context, intent: Intent) {
    RetrofitClient.createService(MovieService::class.java).getMovies().enqueue(object :
        Callback<BaseResponse<Movie>> {
      override fun onFailure(call: Call<BaseResponse<Movie>>, t: Throwable) {
        Log.e(ReleaseReminderManager::class.java.simpleName, t.localizedMessage)
      }

      override fun onResponse(call: Call<BaseResponse<Movie>>,
          response: Response<BaseResponse<Movie>>) {
        if (response.isSuccessful) {
          response.body()?.results?.forEach {
            if (isReleaseToday(it.released)) {
              NotificationHelper(context).createNotification(it.title,
                  context.getString(R.string.release_message, it.title))
            }
          }
        }
      }
    })
  }

  fun setRepeatingAlarm(context: Context, time: String) {
    if (isDateInvalid(time, TIME_FORMAT)) return
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val alarmIntent = Intent(context, ReleaseReminderManager::class.java).let { intent ->
      PendingIntent.getBroadcast(context, ID_REPEATING_RELEASE, intent,
          PendingIntent.FLAG_UPDATE_CURRENT)
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
    val pendingIntent = Intent(context, ReleaseReminderManager::class.java).let {
      PendingIntent.getBroadcast(context, ID_REPEATING_RELEASE, it,
          PendingIntent.FLAG_UPDATE_CURRENT)
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

  private fun isReleaseToday(date: String?): Boolean {
    return try {
      val df = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
      val now = df.format(Date())
      now == date
    } catch (e: ParseException) {
      false
    }
  }
}