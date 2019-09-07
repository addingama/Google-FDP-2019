package com.google.fdp.favouritemovie;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadMoviesCallback {

    private List<Movie> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private DataObserver dataObserver;
    private Context mContext;

    RecyclerView rvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        movieAdapter = new MovieAdapter(this, movies);
        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                Toast.makeText(mContext, "Clicked " + data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        rvMovie = findViewById(R.id.rvMovie);


        rvMovie.setAdapter(movieAdapter);
        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        dataObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(DatabaseContract.MovieColumns.CONTENT_URI, true, dataObserver);
        new MovieAsyncTask(this, this).execute();
    }

    @Override
    public void postExecute(Cursor movieCursor) {
        if (movieCursor != null) {
            this.movies.clear();
            while (movieCursor.moveToNext()){
                this.movies.add(new Movie(movieCursor));
            }
            movieAdapter.notifyDataSetChanged();
        }
    }

    private static class MovieAsyncTask extends AsyncTask<Void, Void,Cursor> {
        private WeakReference<Context> weakContext;
        private WeakReference<LoadMoviesCallback> weakCallback;

        MovieAsyncTask(Context context, LoadMoviesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            if (weakContext.get() != null && weakContext.get().getContentResolver() != null) {
                return weakContext.get().getContentResolver().query(DatabaseContract.MovieColumns.CONTENT_URI, null, null, null, null);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            if (weakCallback.get() != null) {
                weakCallback.get().postExecute(cursor);
            }
        }
    }

    static class DataObserver extends ContentObserver {
        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new MovieAsyncTask(context, (MainActivity) context).execute();
        }
    }
}
