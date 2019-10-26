package com.alfianh.moviecatalog.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alfianh.moviecatalog.R
import com.alfianh.moviecatalog.models.Movie
import com.bumptech.glide.Glide

class MovieAdapter(
  private val context: Context?,
  private val movies: List<Movie>,
  private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int = movies.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bindItem(movies[position])
    holder.itemView.setOnClickListener { onClick(position) }
  }

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ivPoster = view.findViewById<ImageView>(R.id.ivPoster)
    private val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
    private val tvReleased = view.findViewById<TextView>(R.id.tvReleased)
    private val rbRating = view.findViewById<RatingBar>(R.id.rbRating)
    private val tvRating = view.findViewById<TextView>(R.id.tvRating)
    fun bindItem(movie: Movie) {
      with(movie) {
        Glide.with(ivPoster.context).load(posterFullUrl).into(ivPoster)
        tvTitle.text = title
        tvReleased.text = released
        rbRating.rating = rating
        tvRating.text = "($rating)"
      }
    }
  }
}