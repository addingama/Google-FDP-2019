package com.google.fdp.moviecataloguev2.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by gama on 2019-09-07.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class DailyReminderManager extends BroadcastReceiver {
    public static String MESSAGE_KEY = "MESSAGE_KEY";
    public static String TITLE_KEY = "TITLE_KEY";
    public static int ID_REPEATING_DAILY = 1002;
    public static String TIME_FORMAT = "HH:mm";

    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra(TITLE_KEY);
        String message = intent.getStringExtra(MESSAGE_KEY);
        new NotificationHelper(context).createNotification(title, message);
    }

    public void setRepeatingAlarm(Context context, String title, String message, String time) {
        if (isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, DailyReminderManager.class);
        alarmIntent.putExtra(TITLE_KEY, title);
        alarmIntent.putExtra(MESSAGE_KEY, message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,ID_REPEATING_DAILY, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time.split(":")[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(time.split(":")[1]));
        calendar.set(Calendar.SECOND,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminderManager.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING_DAILY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        pendingIntent.cancel();
        alarmManager.cancel(pendingIntent);
    }


    private boolean isDateInvalid(@NonNull  String date, @NonNull String format) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.parse(date);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }
}
