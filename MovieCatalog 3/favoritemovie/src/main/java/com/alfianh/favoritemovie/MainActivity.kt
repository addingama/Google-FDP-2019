package com.alfianh.favoritemovie

import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfianh.favoritemovie.DatabaseContract.MovieColumns.Companion.CONTENT_URI
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), LoadMoviesCallback {

  private var movies: MutableList<Movie> = mutableListOf()
  private lateinit var movieAdapter: MovieAdapter
  private lateinit var dataObserver: DataObserver

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    movieAdapter = MovieAdapter(this, movies) {
      Toast.makeText(this, "Clicked ${movies[it].title}", Toast.LENGTH_SHORT).show()
    }
    rvMovie.adapter = movieAdapter
    rvMovie.layoutManager = LinearLayoutManager(this)
    val handlerThread = HandlerThread("DataObserver")
    handlerThread.start()
    val handler = Handler(handlerThread.looper)
    dataObserver = DataObserver(handler, this)
    contentResolver.registerContentObserver(CONTENT_URI, true, dataObserver)
    MovieAsyncTask(this, this).execute()
  }

  override fun postExecute(movieCursor: Cursor?) {
    movieCursor?.let {
      this.movies.clear()
      while (movieCursor.moveToNext()) {
        this.movies.add(Movie.fromCursor(movieCursor))
      }
      movieAdapter.notifyDataSetChanged()
    }
  }

  private class MovieAsyncTask constructor(context: Context, callback: LoadMoviesCallback) :
      AsyncTask<Void, Void, Cursor?>() {
    private var weakContext: WeakReference<Context> = WeakReference(context)
    private var weakCallback: WeakReference<LoadMoviesCallback> = WeakReference(callback)

    override fun doInBackground(vararg voids: Void): Cursor? {
      return weakContext.get()?.contentResolver?.query(CONTENT_URI, null, null, null, null)
    }

    override fun onPostExecute(data: Cursor?) {
      super.onPostExecute(data)
      weakCallback.get()?.postExecute(data)
    }
  }

  internal class DataObserver(handler: Handler, private val context: Context) :
      ContentObserver(handler) {
    override fun onChange(selfChange: Boolean) {
      super.onChange(selfChange)
      MovieAsyncTask(context, context as MainActivity).execute()
    }
  }
}
