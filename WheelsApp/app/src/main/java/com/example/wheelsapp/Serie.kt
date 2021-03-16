package com.example.wheelsapp

import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class Serie (@SerializedName("id") val id: Long,
                  @SerializedName("title") val title: String,
                  @SerializedName("overview") val overview: String,
                  @SerializedName("poster_path") val posterPath: String,
                  @SerializedName("backdrop_path") val backdropPath: String,
                  @SerializedName("vote_average") val rating: Float,
                  @SerializedName("release_date") val releaseDate: String,
                  @SerializedName("tagline") val tagline: String
):Serializable