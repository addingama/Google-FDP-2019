package com.alfianh.moviecatalog.models.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
  @SerializedName("results")
  val results: List<T>? = null
}