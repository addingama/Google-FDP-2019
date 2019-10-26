package com.alfianh.favoritemovie

import android.database.Cursor

interface LoadMoviesCallback {
  fun postExecute(notes: Cursor?)
}