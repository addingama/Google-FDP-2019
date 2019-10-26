package com.google.fdp.moviecataloguev2.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.fdp.moviecataloguev2.BuildConfig;
import com.google.fdp.moviecataloguev2.R;
import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.models.responses.BaseResponse;
import com.google.fdp.moviecataloguev2.networks.MovieService;
import com.google.fdp.moviecataloguev2.networks.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gama on 2019-09-07.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class ReleaseReminderManager extends BroadcastReceiver {
    public static int ID_REPEATING_RELEASE = 1001;
    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String TIME_FORMAT = "HH:mm";


    @Override
    public void onReceive(Context context, Intent intent) {
        new RetrofitClient().createService(MovieService.class).getMovieReleaseToday(BuildConfig.API_KEY, currentDate(), currentDate()).enqueue(new Callback<BaseResponse<Movie>>() {
            @Override
            public void onResponse(Call<BaseResponse<Movie>> call, Response<BaseResponse<Movie>> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                        if (response.body().results != null) {
                            for (Movie movie : response.body().results) {
                                new NotificationHelper(context).createNotification(
                                        movie.getTitle(),
                                        context.getString(R.string.release_message, movie.getTitle()));
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Movie>> call, Throwable t) {
                Log.e(ReleaseReminderManager.class.getSimpleName(), t.getLocalizedMessage());
            }
        });


    }

    public void setRepeatingAlarm(Context context, String time) {
        if (isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, ReleaseReminderManager.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,ID_REPEATING_RELEASE, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time.split(":")[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(time.split(":")[1]));
        calendar.set(Calendar.SECOND,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReminderManager.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_REPEATING_RELEASE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        pendingIntent.cancel();
        alarmManager.cancel(pendingIntent);
    }


    private boolean isDateInvalid(@NonNull String date, @NonNull String format) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format, Locale.getDefault());
            df.parse(date);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

    private boolean isReleaseToday(@NonNull  String date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String now = df.format(new Date());

        return date.equals(now);
    }

    private String currentDate() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return df.format(new Date());
    }
}
