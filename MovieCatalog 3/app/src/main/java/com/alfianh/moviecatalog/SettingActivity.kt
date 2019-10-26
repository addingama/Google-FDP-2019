package com.alfianh.moviecatalog

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.alfianh.moviecatalog.alarm.DailyReminderManager
import com.alfianh.moviecatalog.alarm.ReleaseReminderManager
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

  private lateinit var mDailyReminderManager: DailyReminderManager
  private lateinit var mReleaseReminderManager: ReleaseReminderManager

  companion object {
    const val RELEASE_REMINDER = "release_reminder"
    const val DAILY_REMINDER = "daily_reminder"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_setting)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.reminder_setting)
    mDailyReminderManager = DailyReminderManager()
    mReleaseReminderManager = ReleaseReminderManager()
    scReleaseReminder.isChecked = checkReminder(RELEASE_REMINDER)
    scDailyReminder.isChecked = checkReminder(DAILY_REMINDER)
    scReleaseReminder.setOnCheckedChangeListener { _, isChecked ->
      saveReminder(RELEASE_REMINDER, isChecked)
      if (isChecked) {
        mReleaseReminderManager.setRepeatingAlarm(this, "08:00")
      } else {
        mReleaseReminderManager.cancelAlarm(this)
      }
    }
    scDailyReminder.setOnCheckedChangeListener { _, isChecked ->
      saveReminder(DAILY_REMINDER, isChecked)
      if (isChecked) {
        mDailyReminderManager.setRepeatingAlarm(this, getString(R.string.daily_notification_title),
            getString(R.string.daily_notification_message), "07:00")
      } else {
        mDailyReminderManager.cancelAlarm(this)
      }
    }
  }

  private fun saveReminder(key: String, value: Boolean) {
    val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
    with(sharedPref.edit()) {
      putBoolean(key, value)
      commit()
    }
  }

  private fun checkReminder(key: String): Boolean = getPreferences(Context.MODE_PRIVATE)?.let {
    it.getBoolean(key, false)
  } ?: false

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> {
        finish()
      }
    }
    return super.onOptionsItemSelected(item)
  }
}
