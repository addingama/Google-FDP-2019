package com.google.fdp.moviecataloguev2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.fdp.moviecataloguev2.alarm.DailyReminderManager;
import com.google.fdp.moviecataloguev2.alarm.ReleaseReminderManager;

public class SettingActivity extends AppCompatActivity {

    private static String RELEASE_REMINDER = "release_reminder";
    private static String DAILY_REMINDER = "daily_reminder";

    DailyReminderManager mDailyReminderManager;
    ReleaseReminderManager mReleaseReminderManager;

    Context mContext;

    SwitchCompat scReleaseReminder;
    SwitchCompat scDailyReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mContext = this;
        scReleaseReminder = findViewById(R.id.scReleaseReminder);
        scDailyReminder = findViewById(R.id.scDailyReminder);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(getString(R.string.reminder_setting));
        }

        mDailyReminderManager = new DailyReminderManager();
        mReleaseReminderManager = new ReleaseReminderManager();

        scReleaseReminder.setChecked(checkReminder(RELEASE_REMINDER));
        scDailyReminder.setChecked(checkReminder(DAILY_REMINDER));

        scReleaseReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mReleaseReminderManager.setRepeatingAlarm(mContext, "08:00");
                } else {
                    mReleaseReminderManager.cancelAlarm(mContext);
                }
            }
        });


        scDailyReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mDailyReminderManager.setRepeatingAlarm(mContext, getString(R.string.daily_notification_title),
                            getString(R.string.daily_notification_message), "07:00");
                } else {
                    mDailyReminderManager.cancelAlarm(mContext);
                }
            }
        });

    }

    private void saveReminder(String key, Boolean value) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(key, value).apply();
        }
    }

    private Boolean checkReminder(String key) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean(key, false);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
